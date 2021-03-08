package com.herokuapp.remitlytask.Controllers;


import com.herokuapp.remitlytask.ConsumerModels.Rate;
import com.herokuapp.remitlytask.Exceptions.SencondaryApiException;
import com.herokuapp.remitlytask.Services.NBPApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@CrossOrigin
@Validated
public class GBPToPLNController {

    @Autowired
    private NBPApiService NBPApiService;

    @GetMapping("GBPToPLN/{GBP}")
    public BigDecimal convertGBPToPLN(@Pattern(regexp = "^\\s*([0-9]+\\.[0-9]{1,2})|([0-9]+)\\s*$") @PathVariable("GBP") String GBP) throws SencondaryApiException {
        Rate rate = NBPApiService.getExchangeRate();
        BigDecimal GBPBigDecimal = new BigDecimal(GBP);
        BigDecimal result = GBPBigDecimal.multiply(rate.getMid());
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }

}
