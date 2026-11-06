package se.nordflow.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

// No default user with a generated password, inventory only uses the JWT from auth
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class NordFlowInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NordFlowInventoryServiceApplication.class, args);
    }

}
