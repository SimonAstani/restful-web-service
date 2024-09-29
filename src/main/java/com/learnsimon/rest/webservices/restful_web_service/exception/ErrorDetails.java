package com.learnsimon.rest.webservices.restful_web_service.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String detials;

    public ErrorDetails(LocalDateTime timestamp, String message, String detials) {
        this.timestamp = timestamp;
        this.message = message;
        this.detials = detials;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetials() {
        return detials;
    }
    //timestamp
    //Errormessage
    //Detials
}
