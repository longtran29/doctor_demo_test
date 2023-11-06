package com.doctor.anywhere.task.exception;

import com.doctor.anywhere.task.dto.response.StatusResponseDTO;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final Integer code;

    public CustomException() {
        this.code = null;
    }

    public CustomException(String message) {
        super(message);
        this.code = null;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.code = null;
    }

    public CustomException(int code) {
        this.code = Integer.valueOf(code);
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = Integer.valueOf(code);
    }

    public CustomException(int code, Throwable cause) {
        super(cause);
        this.code = Integer.valueOf(code);
    }

    public CustomException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = Integer.valueOf(code);
    }

    public CustomException(int code, String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = Integer.valueOf(code);
    }

    public Integer getCode() {
        return this.code;
    }

    public CustomException(StatusResponseDTO response) {
        super(response.getMessage());
        this.code = Integer.valueOf(response.getCode());

    }
}
