package com.xplmc.learning.homemade.gateway.runner;

import com.xplmc.learning.homemade.gateway.client.WhitelistClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * example show how feign works
 *
 * @author luke
 */
@Component
public class WhitelistFeignRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistFeignRunner.class);

    private WhitelistClient whitelistClient;

    @Autowired
    public WhitelistFeignRunner(WhitelistClient whitelistClient) {
        this.whitelistClient = whitelistClient;
    }

    @Override
    public void run(String... strings) {
        try {
            logger.info("echo: {}", whitelistClient.echo("haha"));
        } catch (Exception e) {
            logger.error("whitelist feign runner error", e);
        }
    }

}
