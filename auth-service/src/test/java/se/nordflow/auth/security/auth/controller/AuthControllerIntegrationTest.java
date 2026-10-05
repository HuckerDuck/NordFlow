package se.nordflow.auth.security.auth.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.nordflow.auth.AbstractIntegrationTest;
import se.nordflow.auth.user.model.User;
import se.nordflow.auth.user.repository.UserRepository;

class AuthControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String REGISTER_URL = "/api/auth/register";
    private static final String EMAIL = "anna@nordflow.se";
    private static final String PASSWORD = "Secret123!";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    @Test
    void register_withValidData_createsUserAndReturnsIt() throws Exception {
        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson(EMAIL)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.firstName").value("Anna"))
                .andExpect(jsonPath("$.password").doesNotExist());

        User savedUser = userRepository.findByEmail(EMAIL).orElseThrow();
        assertThat(savedUser.getPassword()).isNotEqualTo(PASSWORD);
        assertThat(savedUser.getPassword()).startsWith("$2a$");
    }

    private String registerJson(String email) {
        return """
                {
                  "email": "%s",
                  "password": "%s",
                  "firstName": "Anna",
                  "lastName": "Svensson",
                  "phoneNumber": "0701234567"
                }
                """.formatted(email, PASSWORD);
    }
}
