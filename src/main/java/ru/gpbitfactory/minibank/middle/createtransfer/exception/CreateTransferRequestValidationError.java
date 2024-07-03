package ru.gpbitfactory.minibank.middle.createtransfer.exception;

import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class CreateTransferRequestValidationError extends AbstractRestApiException {

    @Serial
    private static final long serialVersionUID = -6470484744257053081L;

    public CreateTransferRequestValidationError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
