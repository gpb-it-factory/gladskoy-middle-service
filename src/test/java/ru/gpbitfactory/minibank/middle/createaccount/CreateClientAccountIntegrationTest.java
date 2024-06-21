package ru.gpbitfactory.minibank.middle.createaccount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequestV2;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.createaccount.restclient.BackendServiceUsersApiClient;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateClientAccountIntegrationTest extends AbstractControllerTest {

    @MockBean
    private BackendServiceUsersApiClient usersApiClient;

    @Test
    @DisplayName("Клиент зарегистрирован и счёт доступен для открытия")
    void whenRequestIsValid_thenShouldReturnStatus201() throws Exception {
        when(usersApiClient.getUserByTelegramId(1)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        var createAccountRequest = new CreateAccountRequestV2("Тестовый 1");
        when(usersApiClient.createUserAccount(1, createAccountRequest)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        var createClientAccountRequest = postApiV1("/clients/{telegramId}/accounts", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "accountName": "Тестовый 1" }
                        """);

        mockMvc.perform(createClientAccountRequest)
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.message", is("Счёт успешно открыт!"))
                );
    }
}