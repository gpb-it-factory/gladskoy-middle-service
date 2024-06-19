package ru.gpbitfactory.minibank.middle.createclient.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.createclient.service.ClientsService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientsControllerCreateNewClientTest extends AbstractControllerTest {

    @MockBean
    private ClientsService clientsService;

    @Test
    void whenPassToRequestBodyValidTelegramUserId_thenShouldReturnStatus200() throws Exception {
        var createClientRequest = post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": 1 }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenPassToRequestBodyInvalidTelegramUserId_thenShouldReturn400() throws Exception {
        var createClientRequest = post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": "string" }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPassToRequestBodyEmptyTelegramUserId_thenShouldReturn422() throws Exception {
        var createClientRequest = post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": " " }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is("telegramUserId - не может быть пустым")));
    }

    @Test
    void whenNoPassToRequestBodyEmptyTelegramUserId_thenShouldReturn422() throws Exception {
        var createClientRequest = post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is("telegramUserId - не может быть пустым")));
    }

    @Test
    void whenTelegramUserIdParamIsEqualToZero_thenShouldReturn422() throws Exception {
        var createClientRequest = post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": 0 }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is("telegramUserId - может быть только положительным целым числом больше 1")));
    }

    @Test
    void whenTelegramUserIdLengthIsGreaterThan16_thenShouldReturn422() throws Exception {
        var createClientRequest = post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": 12345678901234567 }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is("telegramUserId - может содержать только число от 1 до 16 символов")));
    }
}