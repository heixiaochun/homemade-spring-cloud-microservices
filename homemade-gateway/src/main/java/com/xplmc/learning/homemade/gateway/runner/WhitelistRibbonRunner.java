package com.xplmc.learning.homemade.gateway.runner;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.xplmc.learning.homemade.gateway.common.GatewayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * example show how Ribbon works
 *
 * @author luke
 */
@Component
public class WhitelistRibbonRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistRibbonRunner.class);

    private DiscoveryClient discoveryClient;

    @Autowired
    public WhitelistRibbonRunner(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void run(String... strings) {
        //get all whitelist server list and put them into list
        List<Server> serverList = discoveryClient.getInstances(GatewayConstants.WHITELIST_SERVER_ID)
                .stream().map(si -> new Server(si.getHost(), si.getPort())).collect(Collectors.toList());

        //create a RoundRobinRule load balancer
        BaseLoadBalancer lb = LoadBalancerBuilder.newBuilder().withRule(new RoundRobinRule())
                .buildFixedServerListLoadBalancer(serverList);

        //test load balancer selection rule
        IntStream.range(0, 10).forEach(i -> {
            Server currentServer = lb.chooseServer();
            logger.info("count: {}, selected: {}", i, currentServer);
        });
    }

}
