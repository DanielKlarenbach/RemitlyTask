package com.herokuapp.remitytask.remitytask;

import com.herokuapp.remitytask.remitytask.Controllers.GBPToPLNController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RemityTaskApplicationTests {

    @Autowired
    private GBPToPLNController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getGBPToPLNRate()

}
