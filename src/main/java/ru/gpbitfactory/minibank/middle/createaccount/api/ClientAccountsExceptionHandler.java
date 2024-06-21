package ru.gpbitfactory.minibank.middle.createaccount.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gpbitfactory.minibank.middle.createaccount.dto.ErrorResponse;
import ru.gpbitfactory.minibank.middle.createaccount.exception.AbstractRestApiException;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class ClientAccountsExceptionHandler {

    @ExceptionHandler
    private <T extends AbstractRestApiException> ResponseEntity<ErrorResponse> handleException(T e) {
        log.warn(e.getMessage());
        var body = ErrorResponse.builder()
                .message(e.getMessage())
                .type(e.getClass().getSimpleName())
                .code(e.getStatus().toString())
                .traceId(UUID.randomUUID())
                .build();
        return new ResponseEntity<>(body, e.getStatus());
    }
}
