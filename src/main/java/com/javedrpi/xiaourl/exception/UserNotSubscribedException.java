package com.javedrpi.xiaourl.exception;

import lombok.Getter;

@Getter
public class UserNotSubscribedException extends RuntimeException{

    private String refId;
    private String message;

    public UserNotSubscribedException(String message) {
        super(message);
    }

    public UserNotSubscribedException(String message, String refId) {
        this.message = message;
        this.refId = refId;
    }
}
