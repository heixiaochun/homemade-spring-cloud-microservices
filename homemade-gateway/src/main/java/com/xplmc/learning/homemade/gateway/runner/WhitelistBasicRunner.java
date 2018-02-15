package com.xplmc.learning.homemade.gateway.runner;

import com.xplmc.learning.homemade.gateway.common.GatewayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

/**
 * example show how discovery client works
 *
 * @author luke
 */
@Component
public class WhitelistBasicRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistBasicRunner.class);

    private DiscoveryClient discoveryClient;

    @Autowired
    public WhitelistBasicRunner(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void run(String... strings) {
        logger.info("all registered services list, {}", discoveryClient.getServices());

        //list all whitelist services
        String serviceId = GatewayConstants.WHITELIST_SERVER_ID;
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
