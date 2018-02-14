package com.xplmc.learning.whitelist.client.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.xplmc.learning.whitelist.client.common.HomemadeWhitelistServiceProperties;
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
 * load balanced client runner example, run after application started
 *
 * @author luke
 */
@Component
public class WhitelistLoadBalancedRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistLoadBalancedRunner.class);

    private RestTemplate restTemplate;

    private HomemadeWhitelistServiceProperties homemadeWhitelistServiceProperties;

    @Autowired
    public WhitelistLoadBalancedRunner(RestTemplate restTemplate, HomemadeWhitelistServiceProperties homemadeWhitelistServiceProperties) {
        this.restTemplate = restTemplate;
        this.homemadeWhitelistServiceProperties = homemadeWhitelistServiceProperties;
    }

    @Override
    public void run(String... strings) throws Exception {
        //construct the simple echo uri
        String uriString = "//" + homemadeWhitelistServiceProperties.getName() +
                homemadeWhitelistServiceProperties.getEchoPath();

        IntStream.range(0, 10).forEach(i -> {
            //construct request variable
            Map<String, String> requestMap = Collections.singletonMap("text",
                    RandomStringUtils.randomAlphabetic(10));

            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(uriString, JsonNode.class, requestMap);
            logger.info("count: {}, responseEntity: {}", i, responseEntity);
        });
    }

}
