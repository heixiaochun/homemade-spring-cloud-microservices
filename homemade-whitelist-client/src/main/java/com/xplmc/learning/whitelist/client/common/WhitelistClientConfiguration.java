package com.xplmc.learning.whitelist.client.common;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * whitelist client specific configuration
 */
@Configuration
public class WhitelistClientConfiguration {

    /**
     * load balanced rest template
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
