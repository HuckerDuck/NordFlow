package se.nordflow.auth.security.auth.dto;

public record ResponseDTO(
        String email,
        String firstName,
        String lastName,
        String phoneNumber
) {
}
