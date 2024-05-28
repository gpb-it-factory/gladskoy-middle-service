package ru.gpbitfactory.minibank.middle.restclient.mock;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record BackendUserAccountMockDto(UUID accountId, String accountName, BigDecimal amount) {

}
