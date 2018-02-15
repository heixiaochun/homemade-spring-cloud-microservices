package com.xplmc.learning.whitelist.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * homemade whitelist client example
 *
 * @author luke
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WhitelistClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitelistClientApplication.class);
    }

}
