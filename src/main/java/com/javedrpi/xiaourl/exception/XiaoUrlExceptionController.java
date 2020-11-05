package com.javedrpi.xiaourl.exception;

import com.javedrpi.xiaourl.model.GlobalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class XiaoUrlExceptionController {

    @ExceptionHandler(value = UserNotSubscribedException.class)
    public ResponseEntity<GlobalError> userNotSubscribedException(UserNotSubscribedException exception){
        GlobalError error = new GlobalError(exception.getMessage(), getRefId(exception.getRefId()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidAuthTokenException.class)
    public ResponseEntity<GlobalError> invalidAuthTokenException(InvalidAuthTokenException exception){
        GlobalError error = new GlobalError(exception.getMessage(), getRefId(exception.getRefId()));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    public String getRefId(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(6, '-');
        return sb.toString();
    }
}
