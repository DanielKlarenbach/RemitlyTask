package com.herokuapp.remitytask.remitytask.Exceptions;

public class NBPApiException extends Exception {

    public NBPApiException(String message) {
        super(message);
    }

    public String toString() {
        return ("Exception occured due to error in NBP api" + this.getMessage());
    }
}