package ru.gpbitfactory.minibank.middle.getclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@Getter
public abstract class AbstractRestApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8424144419011885994L;

    private final HttpStatusCode status;

    protected AbstractRestApiException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }
}
