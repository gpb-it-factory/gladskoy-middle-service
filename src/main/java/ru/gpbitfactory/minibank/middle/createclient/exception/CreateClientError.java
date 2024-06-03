package ru.gpbitfactory.minibank.middle.createclient.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class CreateClientError extends AbstractRestApiException {

    @Serial
    private static final long serialVersionUID = -8949627668822185346L;

    public CreateClientError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
