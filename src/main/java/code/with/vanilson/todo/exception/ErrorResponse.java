package code.with.vanilson.todo.exception;

/**
 * ErrorResponse
 *
 * @author vamuhong
 * @version 1.0
 * @since 2025-02-04
 */

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse() {
        // Default constructor
    }

    // Getters and setters
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}