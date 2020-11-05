package com.javedrpi.xiaourl.exception;

import lombok.Getter;

@Getter
public class InvalidAuthTokenException extends RuntimeException{
    private String refId;
    private String message;

    public InvalidAuthTokenException(String message) {
        super(message);
    }

    public InvalidAuthTokenException(String message, String refId) {
        this.message = message;
        this.refId = refId;
    }
}
