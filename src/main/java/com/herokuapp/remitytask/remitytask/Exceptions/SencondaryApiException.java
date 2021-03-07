package com.herokuapp.remitytask.remitytask.Exceptions;

public class NBPApiException extends Exception {

    public NBPApiException(String message) {
        super(message);
    }

    public String toString() {
        return ("Exception occurred due to error in secondary api: " + this.getMessage());
    }
}