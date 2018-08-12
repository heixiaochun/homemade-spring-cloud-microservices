package com.xplmc.learning.homemade.gateway.common;

import com.xplmc.learning.homemade.gateway.common.log.TradeResultClientHttpRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientSpecification;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * homemade gateway specific configuration
 *
 * @author luke
 */
@Configuration
public class GatewayConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GatewayConfiguration.class);

    @Bean
    @Autowired(required = false)
    public SpringClientFactory springClientFactory(List<RibbonClientSpecification> configurations) {
        SpringClientFactory factory = new SpringClientFactory();
        factory.setConfigurations(configurations);
        return factory;
    }

    /**
     * customized RibbonLoadBalancerClient
     * print the reconstructed URI
     *
     * @return
     */
    @Bean
    public LoadBalancerClient loadBalancerClient(SpringClientFactory springClientFactory) {
        return new RibbonLoadBalancerClient(springClientFactory) {
            @Override
            public URI reconstructURI(ServiceInstance instance, URI original) {
                URI reconstructedURI = super.reconstructURI(instance, original);
                logger.info("reconstructedURI: {}", reconstructedURI);
                return reconstructedURI;
            }
        };
    }

    /**
     * load balanced rest template
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(TradeResultClientHttpRequestInterceptor tradeResultClientHttpRequestInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(tradeResultClientHttpRequestInterceptor);
        return restTemplate;
    }

}
