package ru.gpbitfactory.minibank.middle.createaccount.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class ClientsAccountDoesntExistError extends AbstractRestApiException {

    @Serial
    private static final long serialVersionUID = -8949627668822185346L;

    public ClientsAccountDoesntExistError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
