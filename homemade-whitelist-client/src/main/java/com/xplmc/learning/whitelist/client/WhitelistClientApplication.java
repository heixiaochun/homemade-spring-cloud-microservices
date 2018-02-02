package com.xplmc.learning.whitelist.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * homemade whitelist client example
 *
 * @author luke
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WhitelistClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitelistClientApplication.class);
    }

}
