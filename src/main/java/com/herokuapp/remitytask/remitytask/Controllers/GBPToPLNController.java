package com.herokuapp.remitytask.remitytask.Controllers;

import com.herokuapp.remitytask.remitytask.ConsumerModels.ExchangeRates;
import com.herokuapp.remitytask.remitytask.ConsumerModels.Rate;
import com.herokuapp.remitytask.remitytask.Exceptions.NBPApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class GBPToPLNController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("GBPToPLN/{GBP}")
    public float convertGBPToPLN(@PathVariable("GBP") float GBP) throws NBPApiException {
        ExchangeRates exchangeRates = null;
        Rate rate = null;
        try {
            exchangeRates = this.restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json", ExchangeRates.class);
            rate = exchangeRates.getRates().get(0);
        } catch (RestClientResponseException | NullPointerException e) {
            throw new NBPApiException(e.getMessage());
        }
        return GBP * rate.getMid();
    }

}
