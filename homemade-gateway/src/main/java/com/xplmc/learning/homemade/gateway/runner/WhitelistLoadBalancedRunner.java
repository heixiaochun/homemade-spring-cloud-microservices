package com.xplmc.learning.homemade.gateway.runner;

import com.fasterxml.jackson.databind.JsonNode;
import com.xplmc.learning.homemade.gateway.common.constant.GatewayConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * example show how @LoadBalanced works
 *
 * @author luke
 */
@Component
public class WhitelistLoadBalancedRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistLoadBalancedRunner.class);

    private RestTemplate restTemplate;

    @Autowired
    public WhitelistLoadBalancedRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... strings) {
        try {
            //construct the simple echo uri
            String uriString = "http://" + GatewayConstants.WHITELIST_SERVER_ID +
                    GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH;

            IntStream.range(0, 10).forEach(i -> {
                //construct request variable
                Map<String, String> requestMap = Collections.singletonMap("text",
                        RandomStringUtils.randomAlphabetic(10));

                ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(uriString, JsonNode.class, requestMap);
                logger.info("count: {}, responseEntity: {}", i, responseEntity);
            });
        } catch (Exception e) {
            logger.error("whitelist load balance runner error", e);
        }
    }

}
