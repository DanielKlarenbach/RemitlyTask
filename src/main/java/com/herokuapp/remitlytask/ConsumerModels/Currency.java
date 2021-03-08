package com.herokuapp.remitytask.remitlytask.ConsumerModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Currency {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
