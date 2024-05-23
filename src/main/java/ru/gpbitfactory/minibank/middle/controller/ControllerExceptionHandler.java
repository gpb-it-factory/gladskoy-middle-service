package ru.gpbitfactory.minibank.middle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gpbitfactory.minibank.middle.dto.ErrorResponse;
import ru.gpbitfactory.minibank.middle.exception.AbstractRestApiException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    private <T extends AbstractRestApiException> ResponseEntity<ErrorResponse> handleException(T e) {
        var body = new ErrorResponse(e.getStatus(), e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(body, e.getStatus());
    }
}
