package ru.gpbitfactory.minibank.middle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public abstract class AbstractRestApiException extends RuntimeException {

    private final HttpStatusCode status;

    protected AbstractRestApiException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }
}
