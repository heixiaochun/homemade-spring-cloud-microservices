package com.xplmc.learning.homemade.whitelist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SimpleEchoController {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEchoController.class);

    @GetMapping(("/simple/echo/{text}"))
    public Map<String, String> echo(@PathVariable String text) {
        try {
            Thread.sleep((long) (Math.random() * 10L));
        } catch (InterruptedException e) {
            //ignore
        }
        logger.debug("echo: {}", text);
        return Collections.singletonMap("text", text);
    }

}
