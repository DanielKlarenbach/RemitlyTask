package com.herokuapp.remitytask.remitytask.ConsumerModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Rate {
    private String no;
    private String effectiveDate;
    private BigDecimal mid;
}
