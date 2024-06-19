package ru.gpbitfactory.minibank.middle.createclient.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.createclient.service.ClientsService;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientsControllerCreateNewClientV2Test extends AbstractControllerTest {

    @MockBean
    private ClientsService clientsService;

    @Test
    @DisplayName("После обработки валидного запроса, клиент должен быть успешно сохранён в Backend Service")
    void whenPassToRequestBodyValidParams_thenShouldReturnStatus200() throws Exception {
        var createClientRequest = postApiV2("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": 1, "telegramUserName": "ab1" }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isOk());
    }

    /************************************************* telegramUserId *************************************************/

    private static Stream<Arguments> telegramUserIdArgs() {
        return Stream.of(
                Arguments.of("{ \"telegramUserId\": \" \", \"telegramUserName\": \"ab1\" }", "telegramUserId - не может быть пустым"),
                Arguments.of("{ \"telegramUserName\": \"ab1\" }", "telegramUserId - не может быть пустым"),
                Arguments.of("{ \"telegramUserId\": 0, \"telegramUserName\": \"ab1\" }", "telegramUserId - может быть только положительным целым числом больше 1"),
                Arguments.of(" { \"telegramUserId\": 12345678901234567, \"telegramUserName\": \"ab1\" }",
                        "telegramUserId - может содержать только число от 1 до 16 символов")
        );
    }

    @ParameterizedTest
    @MethodSource("telegramUserIdArgs")
    @DisplayName("Если в запросе пришёл невалидный telegramUserId, должно вернуться сообщение об ошибке")
    void whenPassToRequestBodyInvalidTelegramUserId_thenShouldReturnErrorMessage(String requestBody, String expectedResponse) throws Exception {
        var createClientRequest = postApiV2("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is(expectedResponse)));
    }

    @Test
    @DisplayName("Если в запросе пришёл telegramUserId в виде строки, должен вернуться Status Code: 400")
    void whenPassToRequestBodyInvalidTelegramUserId_thenShouldReturn400() throws Exception {
        var createClientRequest = postApiV2("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "telegramUserId": "string" }
                        """);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /************************************************ telegramUserName ************************************************/

    private static Stream<Arguments> telegramUserNameArgs() {
        return Stream.of(
                Arguments.of(" { \"telegramUserId\": 1 }", "telegramUserName - не может быть пустым"),
                Arguments.of("{ \"telegramUserId\": 1, \"telegramUserName\": \" \" }",
                        "telegramUserName - может содержать только латинские буквы и цифры, минимум 3 символа"),
                Arguments.of("{ \"telegramUserId\":1, \"telegramUserName\": \"ab\" }",
                        "telegramUserName - может содержать только латинские буквы и цифры, минимум 3 символа"),
                Arguments.of("{ \"telegramUserId\":1, \"telegramUserName\": \"abc#\" }",
                        "telegramUserName - может содержать только латинские буквы и цифры, минимум 3 символа")
        );
    }

    @ParameterizedTest
    @MethodSource("telegramUserNameArgs")
    @DisplayName("Если в запросе пришёл невалидный telegramUserName, должно вернуться сообщение об ошибке")
    void whenPassToRequestBodyInvalidTelegramUserName_thenShouldReturnErrorMessage(String requestBody, String expectedResponse) throws Exception {
        var createClientRequest = postApiV2("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(createClientRequest)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is(expectedResponse)));
    }
}