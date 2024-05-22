package ru.gpbitfactory.minibank.middle.exception;

import org.springframework.http.HttpStatusCode;

public class CreateClientException extends AbstractRestApiException {

    public CreateClientException(HttpStatusCode status, String message) {
        super(status, message);
    }
}
