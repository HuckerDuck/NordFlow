package se.nordflow.auth.user.dto;

public record    UserResponseDTO (
        String firstName,
        String lastName,
        String email
) {
}
