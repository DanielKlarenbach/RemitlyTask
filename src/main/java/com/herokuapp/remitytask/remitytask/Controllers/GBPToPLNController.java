package com.herokuapp.remitytask.remitytask.Controllers;

import com.herokuapp.remitytask.remitytask.ConsumerModels.ExchangeRates;
import com.herokuapp.remitytask.remitytask.ConsumerModels.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class GBPToPLNController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("GBPToPLN/{GBP}")
    public float convertGBPToPLN(@PathVariable("GBP") float GBP){
        ExchangeRates exchangeRates=this.restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json", ExchangeRates.class);
        Rate rate=exchangeRates.getRates().get(0);
        return GBP*rate.getMid();
    }

}
