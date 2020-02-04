package com.xplmc.learning.homemade.gateway.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Listen for Event
 *
 * @author heixiaochun
 */
@Component
public class DemoEventListener {

    public static final Logger logger = LoggerFactory.getLogger(DemoEventListener.class);

    private RouteLocator routeLocator;

    private DiscoveryClient discoveryClient;

    @Autowired
    public DemoEventListener(RouteLocator routeLocator, DiscoveryClient discoveryClient) {
        this.routeLocator = routeLocator;
        this.discoveryClient = discoveryClient;
    }

    @EventListener(HeartbeatEvent.class)
    public void heatBeatEvent(HeartbeatEvent e) {
        logger.info("received heart beat event: {}", e);
        discoveryClient.getServices().forEach(serviceId -> {
            logger.info("service found! serverId: {}", serviceId);
            discoveryClient.getInstances(serviceId).forEach(server ->
                    logger.info("server info, serviceId: {}, IP: {}, port: {}",
                            server.getServiceId(), server.getHost(), server.getPort())
            );
        });
    }

    @EventListener(RoutesRefreshedEvent.class)
    public void routesRefreshedEvent(RoutesRefreshedEvent e) {
        logger.info("received routes refreshed  event: {}", e);
        routeLocator.getRoutes().forEach(route -> logger.info("route info, route path: {}, serviceId: {}", route.getPath(), route.getId()));
    }

    @PostConstruct
    public void sxxx() {
        routeLocator.getRoutes().forEach(route -> logger.info("route info, id: {}, full path: {}, serviceId: {}",
                route.getId(), route.getFullPath(), route.getLocation()));
    }

}
