package ru.gpbitfactory.minibank.middle.createclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@Getter
public abstract class AbstractRestApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7547726607175163875L;

    private final HttpStatusCode status;

    protected AbstractRestApiException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }
}
