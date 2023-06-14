package com.ecodrive.platform.u202110085.exception;

public class ApiError extends RuntimeException{
    public ApiError() {
        super();
    }

    public ApiError(String message) {
        super(message);
    }
}
