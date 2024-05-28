package ru.gpbitfactory.minibank.middle.restclient.mock;

import lombok.Builder;

import java.util.List;

@Builder
public record BackendUserMockDto(Long id, Long telegramUserId, List<BackendUserAccountMockDto> account) {

}
