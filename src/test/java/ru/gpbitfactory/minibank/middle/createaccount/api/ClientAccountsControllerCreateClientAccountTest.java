package ru.gpbitfactory.minibank.middle.createaccount.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.createaccount.service.ClientAccountsService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientAccountsControllerCreateClientAccountTest extends AbstractControllerTest {

    @MockBean
    private ClientAccountsService accountsService;

    @Test
    @DisplayName("Обработка валидного запроса")
    void whenRequestIsValid_thenShouldReturnStatus201() throws Exception {
        var createClientAccountRequest = postApiV1("/clients/{telegramId}/accounts", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "accountName": "Тестовый" }
                        """);

        mockMvc.perform(createClientAccountRequest)
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.message", is("Счёт успешно открыт!"))
                );
    }

    @Test
    @DisplayName("Идентификатор пользователя в Telegram невалиден")
    void whenTelegramIdIsNotValid_thenShouldReturnStatus400() throws Exception {
        var createClientAccountRequest = postApiV1("/clients/{telegramId}/accounts", "fake")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "accountName": "Тестовый" }
                        """);

        mockMvc.perform(createClientAccountRequest)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Тело запроса не содержит поле accountName")
    void whenAccountNameIsNotPresent_thenShouldReturn422() throws Exception {
        var createClientAccountRequest = postApiV1("/clients/{telegramId}/accounts", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");

        mockMvc.perform(createClientAccountRequest)
                .andDo(print())
                .andExpectAll(
                        status().isUnprocessableEntity(),
                        jsonPath("$.message", is("accountName - не может быть пустым"))
                );
    }

    @Test
    @DisplayName("Наименование счёта не передано во входном запросе")
    void whenAccountNameIsEmpty_thenShouldReturn422() throws Exception {
        var createClientAccountRequest = postApiV1("/clients/{telegramId}/accounts", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "accountName":  ""}
                        """);

        mockMvc.perform(createClientAccountRequest)
                .andDo(print())
                .andExpectAll(
                        status().isUnprocessableEntity(),
                        jsonPath("$.message", is("accountName - не может быть пустым"))
                );
    }

    @Test
    @DisplayName("Не передано тело запроса")
    void whenRequestBodyIsEmpty_thenShouldReturn400() throws Exception {
        var createClientAccountRequest = postApiV1("/clients/{telegramId}/accounts", 1)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(createClientAccountRequest)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}