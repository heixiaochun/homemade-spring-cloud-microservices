package com.xplmc.learning.whitelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * homemade whitelist service example
 *
 * @author luke
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WhitelistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitelistServiceApplication.class);
    }

}
