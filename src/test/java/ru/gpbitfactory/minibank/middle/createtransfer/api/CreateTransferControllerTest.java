package ru.gpbitfactory.minibank.middle.createtransfer.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.createtransfer.service.CreateTransferService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateTransferControllerTest extends AbstractControllerTest {

    @MockBean
    private CreateTransferService createTransferService;

    @Test
    @DisplayName("Обработка валидного запроса")
    void whenRequestIsValid_thenShouldReturnStatus201() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "from": "fromuser", "to": "touser", "amount": 12345.67 }
                        """);

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.message", is("Перевод успешно совершён!"))
                );
    }

    @Test
    @DisplayName("Параметр from не передан")
    void whenFromParamIsNotPresent_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "to": "touser", "amount": 12345.67 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "from - не может быть пустым");
    }

    @Test
    @DisplayName("Параметр from не заполнен")
    void whenFromParamIsEmpty_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "from": "", "to": "touser", "amount": 12345.67 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "from - не может быть пустым");
    }

    @Test
    @DisplayName("Параметр to не передан")
    void whenToParamIsNotPresent_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "from": "fromuser", "amount": 12345.67 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "to - не может быть пустым");
    }

    @Test
    @DisplayName("Параметр to не заполнен")
    void whenToParamIsEmpty_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "from": "fromuser", "to":  "", "amount": 12345.67 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "to - не может быть пустым");
    }

    @Test
    @DisplayName("Параметр amount не передан")
    void whenAmountParamIsNotPresent_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         { "from": "fromuser", "to": "touser" }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "amount - не может быть пустым");
    }

    @Test
    @DisplayName("Параметр amount равен 0")
    void whenAmountParamIsEqualTo0_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         { "from": "fromuser", "to": "touser", "amount": 0 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "amount - может быть только положительным целым числом больше 1");
    }

    @Test
    @DisplayName("Параметр amount отрицательное число")
    void whenAmountParamIsNegative_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         { "from": "fromuser", "to": "touser", "amount": -1 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "amount - может быть только положительным целым числом больше 1");
    }

    @Test
    @DisplayName("Параметр amount отрицательное число")
    void whenAmountParamIsEmpty_thenShouldReturnStatus422() throws Exception {
        var request = postApiV1("/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         { "from": "fromuser", "to": "touser", "amount": -1 }
                        """);

        performRequestAndExpectValidationErrorMessage(request, "amount - может быть только положительным целым числом больше 1");
    }

    private void performRequestAndExpectValidationErrorMessage(RequestBuilder request, String expected) throws Exception {
        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isUnprocessableEntity(),
                        jsonPath("$.message", is(expected)),
                        jsonPath("$.type", is("CreateTransferRequestValidationError")),
                        jsonPath("$.code", is("422 UNPROCESSABLE_ENTITY")),
                        jsonPath("$.traceId", not(emptyString()))
                );
    }
}