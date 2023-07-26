package com.dsm.up.global.exception.handler;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;

    protected ErrorResponse (int status, String message) {
        this.status = status;
        this.message = message;
    }
}