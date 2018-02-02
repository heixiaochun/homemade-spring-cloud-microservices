package com.xplmc.learning.whitelist.client.component;

import com.xplmc.learning.whitelist.client.common.WhitelistClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

/**
 * run after application started
 *
 * @author luke
 */
@Component
public class WhitelistClientRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistClientRunner.class);

    private DiscoveryClient discoveryClient;

    @Autowired
    public WhitelistClientRunner(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("all registered services list, {}", discoveryClient.getServices());

        //list all whitelist services
        String serviceId = WhitelistClientConstants.WHITELIST_SERVICE_NAME;
        logger.info("registered instance of {} in eureka service registry", serviceId);
        discoveryClient.getInstances(serviceId).forEach(this::printServiceInstance);

    }

    /**
     * just print the service instance
     */
    private void printServiceInstance(ServiceInstance si) {
        logger.info("serviceId:{}, uri:{}", si.getServiceId(), si.getUri());
    }
}
