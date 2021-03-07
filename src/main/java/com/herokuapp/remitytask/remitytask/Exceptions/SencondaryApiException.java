package com.herokuapp.remitytask.remitytask.Exceptions;

import java.io.IOException;

public class SencondaryApiException extends IOException {

    public SencondaryApiException(String message) {
        super("Exception occurred due to error in secondary api: " + message);
    }
}