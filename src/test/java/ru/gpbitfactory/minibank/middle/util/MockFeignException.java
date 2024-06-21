package ru.gpbitfactory.minibank.middle.util;

import feign.FeignException;

public final class MockFeignException extends FeignException {

    public MockFeignException(int status) {
        super(status, "Mock error");
    }
}
