package com.herokuapp.remitytask.remitlytask.Services;

import com.herokuapp.remitytask.remitlytask.ConsumerModels.Currency;
import com.herokuapp.remitytask.remitlytask.ConsumerModels.Rate;
import com.herokuapp.remitytask.remitlytask.Exceptions.SencondaryApiException;
import com.herokuapp.remitytask.remitlytask.ErrorHandlers.RTRestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NBPApiService {
    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json";
    private final RestTemplate restTemplate;

    @Autowired
    public NBPApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RTRestTemplateResponseErrorHandler())
                .build();
    }


    public Rate getExchangeRate() throws SencondaryApiException {
        Currency currency = this.restTemplate.getForObject(NBP_API_URL, Currency.class);
        Rate rate = null;
        try {
            rate = currency.getRates().get(0);
        } catch (NullPointerException e) {
            throw new SencondaryApiException(e.getMessage());
        }
        return rate;
    }
}
