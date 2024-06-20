package ru.gpbitfactory.minibank.middle.getclient.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class GetClientAccountsByTelegramIdError extends AbstractRestApiException {

    @Serial
    private static final long serialVersionUID = -6879195740716806067L;

    public GetClientAccountsByTelegramIdError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
