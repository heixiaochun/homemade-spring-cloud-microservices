package com.xplmc.learning.homemade.gateway.common;

import com.xplmc.learning.homemade.gateway.common.log.TradeResultClientHttpRequestInterceptor;
import com.xplmc.learning.homemade.gateway.common.ribbon.MyRibbonLoadBalancedRetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancedRetryFactory;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerContext;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * homemade gateway specific configuration
 *
 * @author luke
 */
@Configuration
public class GatewayConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GatewayConfiguration.class);

    /**
     * customized RibbonLoadBalancerClient
     * print the reconstructed URI
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
        SimpleClientHttpRequestFactory sf = new SimpleClientHttpRequestFactory();
        sf.setConnectTimeout(200);
        sf.setReadTimeout(1000);
        restTemplate.setRequestFactory(sf);
        restTemplate.getInterceptors().add(tradeResultClientHttpRequestInterceptor);
        return restTemplate;
    }

    /**
     * 支持连接超时重试
     */
    @Bean
    public LoadBalancedRetryFactory loadBalancedRetryFactory(final SpringClientFactory springClientFactory) {
        return new RibbonLoadBalancedRetryFactory(springClientFactory) {
            @Override
            public LoadBalancedRetryPolicy createRetryPolicy(String service, ServiceInstanceChooser serviceInstanceChooser) {
                RibbonLoadBalancerContext lbContext = springClientFactory
                        .getLoadBalancerContext(service);
                return new MyRibbonLoadBalancedRetryPolicy(service, lbContext,
                        serviceInstanceChooser, springClientFactory.getClientConfig(service));
            }
        };
    }

}
