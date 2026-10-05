package se.nordflow.auth;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;


// Base class for the testing
// Start a postgres container and then ge give the app the config information that it needs
// No secrets are to be stored in this test code for security reasons

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {

    @ServiceConnection
    static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    // Started once for the whole test run and shared by all test classes.
    // Testcontainers removes it automatically when the JVM stops.
    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void testProperties(DynamicPropertyRegistry registry) {
        registry.add("JWT_SECRET", AbstractIntegrationTest::randomJwtSecret);
        registry.add("JWT_EXPIRATION_MS", () -> "3600000");
        registry.add("SERVER_PORT", () -> "0");
    }

    // A new random key for every test run, 32 bytes as HS256 requires
    private static String randomJwtSecret() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
