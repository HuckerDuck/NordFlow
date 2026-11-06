package se.nordflow.inventory;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;

// Base class for the testing
// Starts a postgres container and gives the app the config it needs

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {

    @ServiceConnection
    static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16-alpine");

    // Started once for the whole test run and shared by all test classes
    static {
        postgres.start();
    }

    // Same random key for the whole run so the tests can make their own tokens with it later
    protected static final String JWT_SECRET = randomJwtSecret();

    @DynamicPropertySource
    static void testProperties(DynamicPropertyRegistry registry) {
        registry.add("INVENTORY_SERVER_PORT", () -> "0");
        registry.add("JWT_SECRET", () -> JWT_SECRET);
    }

    // 32 bytes since HS256 needs at least that
    private static String randomJwtSecret() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
