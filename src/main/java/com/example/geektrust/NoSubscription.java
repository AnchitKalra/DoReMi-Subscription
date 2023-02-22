package com.example.geektrust;

public class NoSubscription extends Exception{
    private String message;

    NoSubscription(String message) {
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
