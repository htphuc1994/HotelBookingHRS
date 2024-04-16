package com.hrs.hotelbooking.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(5000),
    HTTP_REQUEST_EXCEPTION(5001);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
