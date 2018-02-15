package com.xplmc.learning.whitelist.client.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * feign client runner example, run after application started
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
    public void run(String... strings) throws Exception {
        logger.info("echo: {}", whitelistClient.echo("haha"));
    }

}
