package ru.gpbitfactory.minibank.middle.getclient.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetClientControllerTest extends AbstractControllerTest {

    @Test
    @DisplayName("Идентификатор пользователя в Telegram невалиден")
    void whenTelegramIdIsNotValid_thenShouldReturnStatus400() throws Exception {
        performGetApiV1("/clients/{telegramId}", "fake")
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
