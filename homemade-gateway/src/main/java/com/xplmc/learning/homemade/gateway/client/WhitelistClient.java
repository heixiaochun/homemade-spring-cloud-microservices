package com.xplmc.learning.homemade.gateway.client;

import com.xplmc.learning.homemade.gateway.common.GatewayConstants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * whitelist client
 */
@FeignClient(serviceId = GatewayConstants.WHITELIST_SERVER_ID)
public interface WhitelistClient {

    /**
     * whitelist simple echo test client
     */
    @RequestMapping(method = RequestMethod.GET, value = GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH)
    Map<String, String> echo(@PathVariable("text") String text);

}
