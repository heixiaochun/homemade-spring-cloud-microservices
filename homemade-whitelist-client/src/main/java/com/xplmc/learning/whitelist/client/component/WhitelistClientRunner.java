package com.xplmc.learning.whitelist.client.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * run after application started
 *
 * @author luke
 */
@Component
public class WhitelistClientRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistClientRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("here i am");
    }

}
