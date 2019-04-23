package com.xplmc.learning.homemade.whitelist.controller;

import com.xplmc.learning.homemade.whitelist.common.property.MockResponseProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @RequestMapping(("/simple/echo/{text}"))
    public Map<String, String> echo(@PathVariable String text,
                                    @RequestParam(required = false) Long sleepTime,
                                    @RequestBody(required = false) HashMap<String, String> params) {
        // using blow to throw some error in random
        mockOperation();
        // sleep for a certain time
        sleepForAWhile(sleepTime);
        params.put("text", text);
        return params;
    }

    private void mockOperation() {
        logger.info("mockResponseProperties: {}", mockResponseProperties);
        if (mockResponseProperties.isRandomInternalError() && mockResponseProperties.getInternalErrorChance() > Math.random()) {
            throw new RuntimeException("not very luck!");
        }
    }

    private void sleepForAWhile(Long sleepTime) {
        if (sleepTime != null) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                //ignore
            }
        }
    }

}
