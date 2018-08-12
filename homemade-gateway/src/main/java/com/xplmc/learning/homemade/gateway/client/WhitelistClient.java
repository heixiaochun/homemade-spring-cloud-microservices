package com.xplmc.learning.homemade.gateway.client;

import com.xplmc.learning.homemade.gateway.common.constant.GatewayConstants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * whitelist client
 *
 * @author luke
 */
@FeignClient(serviceId = GatewayConstants.WHITELIST_SERVER_ID)
public interface WhitelistClient {

    /**
     * whitelist simple echo test client
     *
     * @param text path variable text
     * @return remote result
     */
    @RequestMapping(method = RequestMethod.GET, value = GatewayConstants.WHITELIST_SIMPLE_ECHO_PATH)
    Map<String, String> echo(@PathVariable("text") String text);

}
