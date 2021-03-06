package com.herokuapp.remitytask.remitytask.Exceptions;

class NBPApiException extends Exception {

    NBPApiException(String message) {
        super(message);
    }

    public String toString() {
        return ("Exception occured due to error in NBP api" + this.getMessage());
    }
}