package com.xplmc.learning.homemade.gateway.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.stereotype.Component;

/**
 * example show how @EnableZuulProxy works
 */
@Component
public class WhitelistZuulRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistZuulRunner.class);

    private RouteLocator routeLocator;

    @Autowired
    public WhitelistZuulRunner(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public void run(String... strings) {
        logger.info("routeLocator: {}", routeLocator);
        routeLocator.getRoutes().forEach(route -> logger.info("route: {}", route));
    }

}
