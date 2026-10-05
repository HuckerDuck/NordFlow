package se.nordflow.auth.exception;

import java.time.LocalDateTime;
import java.util.Map;

// Same error format for every error the API returns
public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors,
        LocalDateTime timestamp
) {
    public ErrorResponseDTO(int status, String error, String message) {
        this(status, error, message, Map.of(), LocalDateTime.now());
    }
}
