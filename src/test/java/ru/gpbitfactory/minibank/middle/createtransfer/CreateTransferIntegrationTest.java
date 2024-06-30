package ru.gpbitfactory.minibank.middle.createtransfer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.gpbitfactory.minibank.backend.dto.CreateTransferRequestV2;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;
import ru.gpbitfactory.minibank.middle.createtransfer.restclient.BackendServiceUsersApiClient;
import ru.gpbitfactory.minibank.middle.util.MockFeignException;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateTransferIntegrationTest extends AbstractControllerTest {

    private static CreateTransferRequestV2 createTransferRequest;

    @MockBean
    private BackendServiceUsersApiClient usersApiClient;

    @BeforeAll
    static void beforeAll() {
        createTransferRequest = CreateTransferRequestV2.builder()
                .from("fromuser")
                .to("touser")
                .amount(BigDecimal.valueOf(12345.67))
                .build();
    }

    @Test
    @DisplayName("Успешный перевод средств")
    void whenRequestIdValid_thenShouldReturnStatus201() throws Exception {
        when(usersApiClient.createTransfer(createTransferRequest)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        var request = postApiV1("/transfers", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "from": "fromuser", "to": "touser", "amount": 12345.67}
                        """);

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.message", is("Перевод успешно совершён!"))
                );
    }

    @Test
    @DisplayName("Во время перевода возникала ошибка")
    void whenMiddleServiceSentError_thenShouldReturnStatus422() throws Exception {
        when(usersApiClient.createTransfer(createTransferRequest)).thenThrow(new MockFeignException(422));

        var request = postApiV1("/transfers", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        { "from": "fromuser", "to": "touser", "amount": 12345.67 }
                        """);

        mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isUnprocessableEntity(),
                        jsonPath("$.message",
                                is("Не удалось осуществить перевод средств от клиента fromuser клиенту touser: ")),
                        jsonPath("$.type", is("CreateTransferError")),
                        jsonPath("$.code", is("422 UNPROCESSABLE_ENTITY")),
                        jsonPath("$.traceId", not(emptyString()))
                );
    }
}