package com.example.geektrust;

public class TopUpException extends Exception{
    private String message;

    TopUpException(String message) {
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
