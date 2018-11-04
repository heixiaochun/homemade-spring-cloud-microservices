package com.xplmc.learning.homemade.gateway.controller;

import com.xplmc.learning.homemade.gateway.common.constant.GatewayConstants;
import com.xplmc.learning.homemade.gateway.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * forward request to homemade-whitelist using LoadBalanced RestTemplate
 *
 * @author luke
 */
@RestController
public class WhitelistController {

    private EchoService echoService;

    @Autowired
    public WhitelistController(EchoService echoService) {
        this.echoService = echoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH)
    public Map<String, String> echo(@PathVariable String text) throws URISyntaxException {
        return echoService.echo(text);
    }

}
