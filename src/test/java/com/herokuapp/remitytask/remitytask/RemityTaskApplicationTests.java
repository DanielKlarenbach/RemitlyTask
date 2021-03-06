package com.herokuapp.remitytask.remitytask;

import com.herokuapp.remitytask.remitytask.Controllers.GBPToPLNController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RemityTaskApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private GBPToPLNController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testConvertGBPToPLN() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/GBPToPLN/4", Float.class)).isInstanceOf(Float.class);
    }

}
