package com.javedrpi.xiaourl.service;

import com.javedrpi.xiaourl.model.Urls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private XiaourlDbRepository xiaourlDbRepository;

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
}
