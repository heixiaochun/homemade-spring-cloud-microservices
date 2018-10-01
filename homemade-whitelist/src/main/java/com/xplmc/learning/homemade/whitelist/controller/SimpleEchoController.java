package com.xplmc.learning.homemade.whitelist.controller;

import com.xplmc.learning.homemade.whitelist.common.property.MockResponseProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * homemade simple echo controller
 *
 * @author luke
 */
@RestController
@EnableConfigurationProperties({MockResponseProperties.class})
public class SimpleEchoController {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEchoController.class);

    private MockResponseProperties mockResponseProperties;

    @Autowired
    public SimpleEchoController(MockResponseProperties mockResponseProperties) {
        this.mockResponseProperties = mockResponseProperties;
    }

    @GetMapping(("/simple/echo/{text}"))
    public Map<String, String> echo(@PathVariable String text) {
        logger.info("mockResponseProperties: {}", mockResponseProperties);
        if (mockResponseProperties.isRandomInternalError() && mockResponseProperties.getInternalErrorChance() > Math.random()) {
            throw new RuntimeException("not very luck!");
        }
        try {
            Thread.sleep((long) (Math.random() * 10L));
        } catch (InterruptedException e) {
            //ignore
        }
        logger.debug("echo: {}", text);
        return Collections.singletonMap("text", text);
    }

}
