package com.javedrpi.xiaourl.utils;

public class MyLogger {
    public static String getMessage(String msg, String requestId) {
        return new StringBuilder(requestId)
                .append(" - ")
                .append(msg)
                .toString();
    }
}
