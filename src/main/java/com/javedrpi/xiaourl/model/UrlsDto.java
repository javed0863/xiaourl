package com.javedrpi.xiaourl.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UrlsDto {
    private String originalUrl;
    private String shortenUrl;
}
