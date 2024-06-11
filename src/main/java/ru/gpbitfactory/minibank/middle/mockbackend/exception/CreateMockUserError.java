package ru.gpbitfactory.minibank.middle.mockbackend.exception;

import feign.FeignException;
import org.springframework.http.HttpStatusCode;

import java.io.Serial;

public class CreateMockUserError extends FeignException {

    @Serial
    private static final long serialVersionUID = 2040550940360373003L;

    public CreateMockUserError(HttpStatusCode statusCode, String message) {
        super(statusCode.value(), message);
    }
}
