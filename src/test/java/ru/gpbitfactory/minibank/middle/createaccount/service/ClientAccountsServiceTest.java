package ru.gpbitfactory.minibank.middle.createaccount.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequestV2;
import ru.gpbitfactory.minibank.middle.AbstractServiceTest;
import ru.gpbitfactory.minibank.middle.createaccount.restclient.BackendServiceUsersApiClient;
import ru.gpbitfactory.minibank.middle.util.MockFeignException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class ClientAccountsServiceTest extends AbstractServiceTest {

    private static final String VALID_ACCOUNT = "Тестовый 1";

    @MockBean
    private BackendServiceUsersApiClient usersApiClient;

    @Autowired
    private ClientAccountsService clientAccountsService;

    @Test
    @DisplayName("Счёт недоступен для открытия")
    void whenNameAccountDoesntContainInConfig_thenCreateUserAccountShouldReturnError() {
        var createAccountRequest = new CreateAccountRequestV2("Fake");
        assertThatThrownBy(() -> clientAccountsService.createUserAccount(1, createAccountRequest))
                .hasMessage("Счёт 'Fake' не существует, поэтому его невозможно открыть");
    }

    @Test
    @DisplayName("Клиент не зарегистрирован в Backend Service")
    void whenClientNotRegisteredYet_thenShouldReturnErrorResponse() {
        when(usersApiClient.getUserByTelegramId(1)).thenThrow(new MockFeignException(404));

        var createAccountRequest = new CreateAccountRequestV2(VALID_ACCOUNT);
        assertThatThrownBy(() -> clientAccountsService.createUserAccount(1, createAccountRequest))
                .hasMessage("Клиент с telegramId: 1 не зарегистрирован");
    }

    @Test
    @DisplayName("Запрос на открытие нового счёта для клиента в Backend Service вернул ошибку")
    void whenCreateUserAccountWasFailed_thenShouldReturnErrorResponse() {
        when(usersApiClient.getUserByTelegramId(1)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        var createAccountRequest = new CreateAccountRequestV2(VALID_ACCOUNT);
        when(usersApiClient.createUserAccount(1, createAccountRequest)).thenThrow(new MockFeignException(500));

        assertThatThrownBy(() -> clientAccountsService.createUserAccount(1, createAccountRequest))
                .hasMessage("Во время регистрации клиента с telegramId: 1 возникла ошибка");
    }
}