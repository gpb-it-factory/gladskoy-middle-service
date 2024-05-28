package ru.gpbitfactory.minibank.middle.exception;

import org.springframework.http.HttpStatusCode;

public class CreateClientError extends AbstractRestApiException {

    public CreateClientError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
