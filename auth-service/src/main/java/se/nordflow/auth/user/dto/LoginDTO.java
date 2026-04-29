package se.nordflow.auth.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "Email is needed and cannot be blank")
        @Email(message = "Email needs to be valid")
        @Size(min = 3, max = 20, message = "Email must be between 3 and 20 characters")
        String email,

        @NotBlank(message = "Email is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters")
        String password
) {
}
