package se.nordflow.inventory.exception;

import java.time.LocalDateTime;
import java.util.Map;

// Same error format as auth-service so the frontend only needs one way to read errors
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
