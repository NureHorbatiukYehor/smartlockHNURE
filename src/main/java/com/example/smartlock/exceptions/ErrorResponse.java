package com.example.smartlock.exceptions;

import java.time.OffsetDateTime;

public class ErrorResponse {
    private String message;
    private String errorCode;
    private OffsetDateTime timestamp;

    public ErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = OffsetDateTime.now();
    }
}
