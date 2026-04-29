package se.nordflow.auth.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record registerDTO(

        @NotBlank (message = "Username is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,

        @NotBlank(message = "Password is needed ")
        @Pattern(
                regexp = "^(?=.*\\p{Ll})(?=.*\\p{Lu})(?=.*\\d)(?=.*[@$!%*?&])[\\p{L}\\d@$!%*?&]{8,}$",
                message = "Password needs atleast 8 charecters, one big letter, one small letter and a special character"
        )
        String password,

        @NotBlank (message = "Email is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "Email must be between 3 and 20 characters")
        @Email(message = "Email needs to be valid")
        String email,

        @NotBlank (message = "Your first name is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "Your first name must be between 3 and 20 characters")
        String firstName,

        @NotBlank (message = "Your last name is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "You last name must be between 3 and 20 characters")
        String lastName,

        @NotBlank (message = "Your phone is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "Phone number must be between 3 and 20 characters")
        String phoneNumber


) {
}
