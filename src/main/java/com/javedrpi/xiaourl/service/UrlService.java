package com.javedrpi.xiaourl.service;

import com.javedrpi.xiaourl.model.Urls;
import com.javedrpi.xiaourl.model.UrlsDto;

import javax.servlet.http.HttpServletRequest;

public interface UrlService {
    Urls insertRecord(String originalUrl, String shortUrl);

    Urls findOriginalUrl(String shortUrl);

    UrlsDto getUrlsDtoResponse(String originalUrl, HttpServletRequest request);

    boolean isValidClient(String clientId, String apiToken);
}
