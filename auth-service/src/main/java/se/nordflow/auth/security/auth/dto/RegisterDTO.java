package se.nordflow.auth.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterDTO(

        @NotBlank (message = "Email is needed and cannot be blank")
        @Size(max = 255, message = "Email can be max 255 characters")
        @Email(message = "Email needs to be valid")
        String email,


        @NotBlank(message = "Password is needed")
        @Size(max = 72, message = "Password can be max 72 characters")
        @Pattern(
                regexp = "^(?=.*\\p{Ll})(?=.*\\p{Lu})(?=.*\\d)(?=.*[@$!%*?&])[\\p{L}\\d@$!%*?&]{8,}$",
                message = "Password needs at least 8 characters, one big letter, one small letter, one number and a special character"
        )
        String password,


        @NotBlank (message = "Your first name is needed and cannot be blank")
        @Size(max = 50, message = "Your first name can be max 50 characters")
        String firstName,

        @NotBlank (message = "Your last name is needed and cannot be blank")
        @Size(max = 50, message = "Your last name can be max 50 characters")
        String lastName,

        @NotBlank (message = "Your phone is needed and cannot be blank")
        @Size(min = 3, max = 20, message = "Phone number must be between 3 and 20 characters")
        String phoneNumber
) {
}
