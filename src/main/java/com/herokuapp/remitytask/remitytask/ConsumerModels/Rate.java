package com.herokuapp.remitytask.remitytask.ConsumerModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Rate {
    private String no;
    private String effectiveDate;
    private Float mid;
}
