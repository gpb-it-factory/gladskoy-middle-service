package ru.gpbitfactory.minibank.middle.getclient.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseV2Inner;
import ru.gpbitfactory.minibank.middle.getclient.exception.GetClientAccountsByTelegramIdError;
import ru.gpbitfactory.minibank.middle.getclient.restclient.BackendServiceUsersApiClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetClientService {

    private final BackendServiceUsersApiClient usersApiClient;

    public List<AccountsListResponseV2Inner> getClientAccounts(long telegramId) {
        log.info("Поступил запрос на получение счетов клиента telegramId: {}", telegramId);
        try {
            log.debug("Отправляем запрос на получение счетов клиента в Backend Service");
            var userAccounts = usersApiClient.getUserAccounts(telegramId).getBody();
            log.info("У клиента telegramId: {} открыто {} счетов", telegramId, userAccounts.size());
            return userAccounts;
        } catch (FeignException e) {
            var errorMessage = String.format("Не удалось найти счета клиента telegramId: %s", telegramId);
            throw new GetClientAccountsByTelegramIdError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }
    }
}
