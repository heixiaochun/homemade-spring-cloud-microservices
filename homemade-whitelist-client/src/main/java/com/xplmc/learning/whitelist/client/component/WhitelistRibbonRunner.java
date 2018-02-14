package com.xplmc.learning.whitelist.client.component;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.xplmc.learning.whitelist.client.common.HomemadeWhitelistServiceProperties;
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
 * ribbon client runner example, run after application started
 *
 * @author luke
 */
@Component
public class WhitelistRibbonRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistRibbonRunner.class);

    private DiscoveryClient discoveryClient;

    private HomemadeWhitelistServiceProperties homemadeWhitelistServiceProperties;

    @Autowired
    public WhitelistRibbonRunner(DiscoveryClient discoveryClient, HomemadeWhitelistServiceProperties homemadeWhitelistServiceProperties) {
        this.discoveryClient = discoveryClient;
        this.homemadeWhitelistServiceProperties = homemadeWhitelistServiceProperties;
    }

    @Override
    public void run(String... strings) throws Exception {
        //get all whitelist server list and put them into list
        List<Server> serverList = discoveryClient.getInstances(homemadeWhitelistServiceProperties.getName())
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
