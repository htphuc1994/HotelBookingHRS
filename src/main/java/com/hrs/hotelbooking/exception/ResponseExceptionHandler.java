package com.hrs.hotelbooking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    @ExceptionHandler({Exception.class, IOException.class, RuntimeException.class})
    protected ResponseEntity<ResponseExceptionBean> handleInternalServerError(Exception e) {
        String detail = e.getLocalizedMessage();
        log.error(detail, e);

        return response(detail, ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ResponseExceptionBean> response(String message, ErrorCode errorCode, HttpStatus status) {
        ResponseExceptionBean responseExceptionBean = ResponseExceptionBean.builder()
                .message(message)
                .errorCode(errorCode.getCode())
                .timestamp(LocalDateTime.now().toString())
                .statusCode(status.value())
                .build();

        return new ResponseEntity<>(responseExceptionBean, new HttpHeaders(), status);
    }

}
