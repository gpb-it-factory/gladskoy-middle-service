package ru.gpbitfactory.minibank.middle.getaccounts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gpbitfactory.minibank.middle.AbstractControllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountsControllerIntegrationTest extends AbstractControllerTest {

    @Test
    @DisplayName("Существует как минимум один счет, доступный для открытия")
    void whenAccountsIsAvailable_thenShouldReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.[0].name", is("Тестовый 1")),
                        jsonPath("$.[0].description", is("Необходимо для тестирования")),
                        jsonPath("$.[0].initAmount", is(5000)),
                        jsonPath("$.[0].type", is("DEFAULT"))
                );
    }
}