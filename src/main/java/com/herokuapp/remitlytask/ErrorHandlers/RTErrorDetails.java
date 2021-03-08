package com.herokuapp.remitytask.remitlytask.ErrorHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RTErrorDetails {

    private Date date;
    private String message;
    private String description;
}
