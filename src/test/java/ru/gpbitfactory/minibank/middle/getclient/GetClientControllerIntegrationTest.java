package ru.gpbitfactory.minibank.middle.getclient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseV2Inner;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.getclient.restclient.BackendServiceUsersApiClient;
import ru.gpbitfactory.minibank.middle.util.MockFeignException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetClientControllerIntegrationTest extends AbstractControllerTest {

    @MockBean
    private BackendServiceUsersApiClient usersApiClient;

    @Test
    @DisplayName("У клиента нет открытых счетов")
    void whenClientHasNoAccountsInBackendService_thenShouldReturnEmptyArrayOfAccounts() throws Exception {
        when(usersApiClient.getUserAccounts(1)).thenReturn(new ResponseEntity<>(List.of(), HttpStatus.OK));

        performGetApiV1("/clients/1")
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accounts", emptyIterable()));
    }

    @Test
    @DisplayName("У клиента есть открытые счета")
    void whenClientHasAccountInBackendService_thenShouldReturnArrayOfAccounts() throws Exception {
        var account = new AccountsListResponseV2Inner(UUID.randomUUID(), "Test Account", BigDecimal.TEN);
        when(usersApiClient.getUserAccounts(1)).thenReturn(new ResponseEntity<>(List.of(account), HttpStatus.OK));

        performGetApiV1("/clients/1")
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.accounts[0].name", is("Test Account")),
                        jsonPath("$.accounts[0].amount", is(10))
                );
    }

    @Test
    @DisplayName("У клиента есть счёт с нулевым балансом")
    void whenClientHasAccountWithZeroBalance_thenShouldReturnArrayOfAccountsWithZeroAmount() throws Exception {
        var account = new AccountsListResponseV2Inner(UUID.randomUUID(), "Test Account", null);
        when(usersApiClient.getUserAccounts(1)).thenReturn(new ResponseEntity<>(List.of(account), HttpStatus.OK));

        performGetApiV1("/clients/1")
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.accounts[0].name", is("Test Account")),
                        jsonPath("$.accounts[0].amount", is(0))
                );
    }

    @Test
    @DisplayName("Во время получения счетов из BackendService произошла ошибка")
    void whenGetUserAccountWasFailed_thenShouldReturnErrorResponse() throws Exception {
        when(usersApiClient.getUserAccounts(2)).thenThrow(new MockFeignException(500));

        performGetApiV1("/clients/2")
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message", is("Не удалось найти счета клиента telegramId: 2")),
                        jsonPath("$.type", is("GetClientAccountsByTelegramIdError")),
                        jsonPath("$.code", is("422 UNPROCESSABLE_ENTITY")),
                        jsonPath("$.traceId", not(emptyString()))
                );
    }
}