package com.xplmc.learning.homemade.gateway.controller;

import com.xplmc.learning.homemade.gateway.common.GatewayConstants;
import com.xplmc.learning.homemade.gateway.common.log.TradeResultKafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * forward request to homemade-whitelist using LoadBalanced RestTemplate
 *
 * @author luke
 */
@RestController
public class WhitelistController {

    private RestTemplate restTemplate;

    private TradeResultKafkaSender tradeResultKafkaSender;

    @Autowired
    public WhitelistController(RestTemplate restTemplate, TradeResultKafkaSender tradeResultKafkaSender) {
        this.restTemplate = restTemplate;
        this.tradeResultKafkaSender = tradeResultKafkaSender;
    }

    @RequestMapping(method = RequestMethod.GET, value = GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH)
    public Map<String, String> echo(@PathVariable String text) throws URISyntaxException {
        // construct request entity
        URI uri = new UriTemplate("http://" + GatewayConstants.WHITELIST_SERVER_ID +
                GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH).expand(text);
        RequestEntity requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON_UTF8).build();

        // construct response type
        ParameterizedTypeReference<HashMap<String, String>> responseType = new ParameterizedTypeReference<HashMap<String, String>>() {
        };

        return restTemplate.exchange(requestEntity, responseType).getBody();
    }

}
