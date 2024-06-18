package ru.gpbitfactory.minibank.middle.createaccount.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequestV2;
import ru.gpbitfactory.minibank.backend.dto.UserResponseV2;
import ru.gpbitfactory.minibank.middle.config.AccountsConfigurationProperties;
import ru.gpbitfactory.minibank.middle.createaccount.exception.ClientsAccountDoesntExistError;
import ru.gpbitfactory.minibank.middle.createaccount.exception.CreateClientAccountError;
import ru.gpbitfactory.minibank.middle.createaccount.exception.GetClientByTelegramIdError;
import ru.gpbitfactory.minibank.middle.createaccount.restclient.BackendServiceUsersApiClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientAccountsService {

    private final BackendServiceUsersApiClient usersApiClient;
    private final AccountsConfigurationProperties accountsConfiguration;

    public void createUserAccount(long telegramId, CreateAccountRequestV2 createAccountRequest) {
        log.info("Поступил запрос на открытие счёта для клиента с telegramId: {}", telegramId);
        try {
            validateAccount(createAccountRequest.getAccountName());

            log.debug("Проверяем зарегистрирован ли клиент в Backend Service");
            getUserByTelegramId(telegramId);

            log.debug("Клиент зарегистрирован в Backend Service, поэтому открываем для клиента счёт");
            usersApiClient.createUserAccount(telegramId, createAccountRequest);
        } catch (FeignException e) {
            var errorMessage = String.format("Во время регистрации клиента с telegramId: %s возникла ошибка", telegramId);
            throw new CreateClientAccountError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }
        log.info("Клиенту с telegramId: {} открыт счёт '{}'", telegramId, createAccountRequest.getAccountName());
    }

    public UserResponseV2 getUserByTelegramId(long telegramId) {
        log.info("Поступил запрос на поиск клиента по telegramId: {}", telegramId);
        try {
            var userResponse = usersApiClient.getUserByTelegramId(telegramId).getBody();
            log.info("Клиент с telegramId: {} успешно найден", telegramId);
            return userResponse;
        } catch (FeignException e) {
            var errorMessage = String.format("Клиент с telegramId: %s не зарегистрирован", telegramId);
            throw new GetClientByTelegramIdError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }
    }

    private void validateAccount(String accountName) {
        log.debug("Проверяем возможно ли открыть клиенту счёт '{}'", accountName);
        var foundAccount = accountsConfiguration.getAccounts().stream()
                .filter(account -> account.getName().equals(accountName))
                .findFirst()
                .orElseThrow(() -> {
                    var errorMessage = String.format("Счёт '%s' не существует, поэтому его невозможно открыть", accountName);
                    return new ClientsAccountDoesntExistError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
                });
        log.debug("Счёт '{}' можно открывать", foundAccount.getName());
    }
}
