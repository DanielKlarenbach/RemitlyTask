package com.herokuapp.remitytask.remitytask.Controllers;

import com.herokuapp.remitytask.remitytask.ConsumerModels.ExchangeRates;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GBPToPLNController {

    @RequestMapping("GBPToPLN/{GBP}")
    public Float convertGBPToPLN(@PathVariable("GBP") Float GBP){
        return null;
    }

    private Float getGBPToPLNRate(){
        return null;
    }
}
