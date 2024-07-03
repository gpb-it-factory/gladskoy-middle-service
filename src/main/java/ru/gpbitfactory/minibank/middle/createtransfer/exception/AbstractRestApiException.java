package ru.gpbitfactory.minibank.middle.createtransfer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

@Getter
public abstract class AbstractRestApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 418108184698017204L;

    private final HttpStatusCode status;

    protected AbstractRestApiException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }
}
