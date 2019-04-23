package com.xplmc.learning.homemade.gateway.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xplmc.learning.homemade.gateway.common.constant.GatewayConstants;
import com.xplmc.learning.homemade.gateway.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Echo Service with Hystrix
 *
 * @author luke
 */
@Service
public class EchoServiceImpl implements EchoService {

    private static final Logger logger = LoggerFactory.getLogger(EchoServiceImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    public EchoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "echoFallback")
    public Map<String, String> echo(String text) {
        // construct request entity
        URI uri = new UriTemplate("http://" + GatewayConstants.WHITELIST_SERVER_ID +
                GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH).expand(text);
        RequestEntity<Map<String, String>> requestEntity = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON_UTF8).body(Collections.singletonMap("hbo", "哈哈哈"));

        // construct response type
        ParameterizedTypeReference<HashMap<String, String>> responseType = new ParameterizedTypeReference<HashMap<String, String>>() {
        };

        return restTemplate.exchange(requestEntity, responseType).getBody();
    }

    /**
     * fallback method for echo
     *
     * @return empty map
     */
    public Map<String, String> echoFallback(String text) {
        logger.warn("error when invoking remote echo, fallback with empty map");
        return new HashMap<>(8);
    }

}
