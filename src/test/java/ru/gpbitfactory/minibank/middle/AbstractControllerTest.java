package ru.gpbitfactory.minibank.middle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    protected MockHttpServletRequestBuilder postApiV1(String urlTemplate, Object... urlVariables) {
        return post("/api/v1".concat(urlTemplate), urlVariables);
    }

    protected MockHttpServletRequestBuilder postApiV2(String urlTemplate) {
        return post("/api/v2".concat(urlTemplate));
    }
}
