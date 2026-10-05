package se.nordflow.auth.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LoginDTO(
        @NotBlank(message = "Email is needed and cannot be blank")
        @Email(message = "Email needs to be valid")
        @Size(max = 255, message = "Email can be max 255 characters")
        String email,

        @NotBlank(message = "Password is needed and cannot be blank")
        String password
) {
}
