package com.herokuapp.remitytask.remitytask.ErrorHandlers;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
public class RTErrorDetails {
    private Date date;
    private String message;
    private String description;


    public RTErrorDetails(Date date, String message, String description) {
        this.date=date;
        this.message=message;
        this.description=description;
    }
}
