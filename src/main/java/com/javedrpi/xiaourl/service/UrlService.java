package com.javedrpi.xiaourl.service;

import com.javedrpi.xiaourl.model.Urls;

public interface UrlService {
    Urls insertRecord(String originalUrl, String shortUrl);

    Urls findOriginalUrl(String shortUrl);
}
