package com.javedrpi.xiaourl.service;

import com.javedrpi.xiaourl.model.Clients;
import com.javedrpi.xiaourl.model.Urls;
import com.javedrpi.xiaourl.model.UrlsDto;
import com.javedrpi.xiaourl.repository.ClientsRepository;
import com.javedrpi.xiaourl.repository.XiaourlDbRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

import static com.javedrpi.xiaourl.utils.MyLogger.getMessage;

@Service
public class UrlServiceImpl implements UrlService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private XiaourlDbRepository xiaourlDbRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    @Qualifier("base62From10")
    private UniqueRandomService uniqueRandomService;

    @Autowired
    private UrlService urlService;

    @Value("${xiao.url.base}")
    private String BASE_XIAOURL;

    @Override
    public Urls insertRecord(String originalUrl, String shortUrl) {
        Urls record = new Urls()
                .with($ -> $.setOriginalUrl(originalUrl))
                .with($ -> $.setShortenUrl(shortUrl))
                .with($ -> $.setIsActive(1))
                .with($ -> $.setExpirationDate(Date.from(LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.ofHours(8)))));

        Urls savedRecord = xiaourlDbRepository.save(record);
        return savedRecord;
    }

    @Override
    public Urls findOriginalUrl(String shortUrl) {
        return xiaourlDbRepository.findByShortenUrl(shortUrl);
    }

    @Override
    public UrlsDto getUrlsDtoResponse(@RequestParam("url") String originalUrl, HttpServletRequest request) {
        String requestId = request.getSession().getId().substring(0,6);

        logger.info(getMessage("Original Url: "+originalUrl, requestId));
        String urlCode = uniqueRandomService.getUniqueRandom(getSeedvalue());
        String shortenUrl = BASE_XIAOURL.concat(urlCode);
        Urls savedRecord = urlService.insertRecord(originalUrl, shortenUrl);
        logger.info(getMessage("Shorten Url: "+savedRecord.getShortenUrl(), requestId));
        return new UrlsDto(savedRecord.getOriginalUrl(), savedRecord.getShortenUrl());
    }

    @Override
    public boolean isValidClient(String clientId, String apiToken) {
        Clients client = clientsRepository.findByClientIdAndApiToken(clientId, apiToken);
        System.out.println(Optional.ofNullable(client).map(o->true).orElse(false));
        return Optional.ofNullable(client).map(o->true).orElse(false);
    }

    synchronized private Long getSeedvalue() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8))
                + LocalDateTime.now().getNano();
    }
}
