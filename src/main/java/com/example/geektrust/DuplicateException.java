package com.example.geektrust;

public class DuplicateException extends Exception{
    private String message;

    DuplicateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
