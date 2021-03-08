package com.herokuapp.remitlytask;

import com.herokuapp.remitlytask.ConsumerModels.Rate;
import com.herokuapp.remitlytask.Controllers.GBPToPLNController;
import com.herokuapp.remitlytask.Services.NBPApiService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(value = GBPToPLNController.class)
public class GBPToPLNControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NBPApiService NBPApiService;
    Rate rate = new Rate("045/A/NBP/2021","2021-03-08", new BigDecimal(5.3478));

    @Test
    public void convertGBPToPLNShouldNotReturnNull() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        float GBP = 4;
        MvcResult result = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBP))).andReturn();

        assertThat(result).isNotNull();
    }

    @Test
    public void convertGBPToPLNTakesIntegerGBPFormat() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        int GBP = 4;
        MvcResult result = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBP))).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertTrue(HttpStatus.valueOf(response.getStatus()).is2xxSuccessful());
    }

    @Test
    public void convertGBPToPLNTakesFloatGBPFormat() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        float GBP = 4.52f;
        MvcResult result = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBP))).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertTrue(HttpStatus.valueOf(response.getStatus()).is2xxSuccessful());
    }

    @Test
    public void convertGBPToPLNReturnsFloatGBPFormat() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        float GBP = 2f;
        MvcResult result = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBP))).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertTrue(response.getContentAsString().matches("^[0-9]+\\.[0-9]{2}$"));
    }

    @Test
    public void convertGBPToPLNRoundsResultCorrectly() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        float GBPFloor = 4;
        float GBPCelling =5;
        MvcResult resultFloor = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBPFloor))).andReturn();
        MvcResult resultCelling = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBPCelling))).andReturn();

        JSONAssert.assertEquals(resultFloor.getResponse()
                .getContentAsString(), "21.39",false);
        JSONAssert.assertEquals(resultCelling.getResponse()
                .getContentAsString(), "26.74",false);
    }

    @Test
    public void convertGBPToPLNDoesNotTakeNegativeGBP() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        float GBP = -4;
        MvcResult result = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBP))).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),response.getStatus());
        assertThat(response.getContentAsString()).contains("\"message\":\"Wrong format of GBP input value.\"");
    }

    @Test
    public void convertGBPToPLNDoesNotTakeGBPWithMoreThanTwoNumbersAfterComma() throws Exception {
        when(NBPApiService.getExchangeRate()).thenReturn(rate);
        float GBP = 4.5666f;
        MvcResult result = this.mockMvc.perform(get("/GBPToPLN/"+String.valueOf(GBP))).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),response.getStatus());
        assertThat(response.getContentAsString()).contains("\"message\":\"Wrong format of GBP input value.\"");
    }
}







