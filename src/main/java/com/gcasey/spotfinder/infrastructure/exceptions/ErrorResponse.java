package com.gcasey.spotfinder.infrastructure.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

// Custom Error class used to display more information about an error
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int code;
    private String status;
    private String message;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }
}

