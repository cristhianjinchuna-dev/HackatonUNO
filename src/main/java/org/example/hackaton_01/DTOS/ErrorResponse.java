package org.example.hackaton_01.DTOS;

import java.time.Instant;

public class ErrorResponse {
    private String error;
    private String message;
    private String timestamp;
    private String path;

    public ErrorResponse(String error, String message, String path) {
        this.error = error;
        this.message = message;
        this.timestamp = Instant.now().toString(); // ISO-8601 UTC
        this.path = path;
    }

    // Getters y Setters
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getTimestamp() { return timestamp; }
    public String getPath() { return path; }
}