package com.xplmc.learning.homemade.whitelist.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * unit test for SimpleEchoController
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SimpleEchoController.class)
public class SimpleEchoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testEcho() throws Exception {
        Assert.assertNotNull("mock mvc should not be null", mvc);
        String expertResultJson = "{\"text\":\"tiger\"}";
        this.mvc.perform(get("/simple/echo/tiger").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json(expertResultJson));
    }

}
