package com.herokuapp.remitytask.remitytask.ConsumerModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ExchangeRates {
    private String table;
    private String currency;
    private String Code;
    private List<Rate> rates;
}
