package com.herokuapp.remitlytask.Services;

import com.herokuapp.remitlytask.ConsumerModels.Currency;
import com.herokuapp.remitlytask.ErrorHandlers.RTRestTemplateResponseErrorHandler;
import com.herokuapp.remitlytask.ConsumerModels.Rate;
import com.herokuapp.remitlytask.Exceptions.SencondaryApiException;
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
