package com.javedrpi.xiaourl.controller;

import com.javedrpi.xiaourl.model.Urls;
import com.javedrpi.xiaourl.model.UrlsDto;
import com.javedrpi.xiaourl.service.UniqueRandomService;
import com.javedrpi.xiaourl.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class LittleUrlController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("base62From10")
    private UniqueRandomService uniqueRandomService;

    @Autowired
    private UrlService urlService;

    @GetMapping("/xiao")
    public ResponseEntity<UrlsDto> xiaoUrl(@RequestParam("url") final String url,
                                           HttpServletRequest request){
        String baseurl = getBaseShortUrl(request);

        String urlCode = uniqueRandomService.getUniqueRandom(getSeedvalue());
        String shortenUrl = baseurl.concat(urlCode);
        Urls savedRecord = urlService.insertRecord(url, shortenUrl);
        UrlsDto urlsDto = new UrlsDto(savedRecord.getOriginalUrl(), savedRecord.getShortenUrl());

        return new ResponseEntity<>(urlsDto, HttpStatus.OK);
    }

    private String getBaseShortUrl(HttpServletRequest request) {
        String baseurl = request.getRequestURL().toString();
        return baseurl.replace("xiao","lu/");
    }

    @GetMapping("/lu/{urlcode}")
    public RedirectView redirectToOriginal(@PathVariable("urlcode") final String urlcode,
                                           HttpServletRequest request){
        logger.info("redirecting...");
        Urls savedRecord = urlService.findOriginalUrl(request.getRequestURL().toString());
        return new RedirectView(savedRecord.getOriginalUrl());
    }

    synchronized private Long getSeedvalue() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8))
                        + LocalDateTime.now().getNano();
    }
}
