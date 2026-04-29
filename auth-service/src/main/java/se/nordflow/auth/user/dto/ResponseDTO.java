package se.nordflow.auth.user.dto;

public record ResponseDTO(
        String email,
        String firstName,
        String lastName,
        String phoneNumber
) {
}
