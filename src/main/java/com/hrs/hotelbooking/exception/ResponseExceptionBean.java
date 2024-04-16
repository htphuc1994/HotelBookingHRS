package com.hrs.hotelbooking.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ResponseExceptionBean {
    private String message;
    private String timestamp;
    private int statusCode;
    private int errorCode;
}