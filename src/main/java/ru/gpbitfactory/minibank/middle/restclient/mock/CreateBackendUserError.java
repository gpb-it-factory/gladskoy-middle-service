package ru.gpbitfactory.minibank.middle.restclient.mock;

import org.springframework.http.HttpStatusCode;
import ru.gpbitfactory.minibank.middle.exception.AbstractRestApiException;

public class CreateBackendUserError extends AbstractRestApiException {

    public CreateBackendUserError(HttpStatusCode status, String message) {
        super(status, message);
    }
}
