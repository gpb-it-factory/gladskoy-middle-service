package ru.gpbitfactory.minibank.middle.createtransfer.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class CreateTransferError extends AbstractRestApiException {

    @Serial
    private static final long serialVersionUID = 4537146363864842220L;

    public CreateTransferError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
