package com.xplmc.learning.whitelist.client.component;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(serviceId = "homemade-whitelist-service")
public interface WhitelistClient {

    @RequestMapping(method = RequestMethod.GET, value = "/simple/echo/{text}")
    Map<String, String> echo(@PathVariable("text") String text);

}
