package com.xplmc.learning.whitelist.client.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("homemade.whitelist.service")
public class HomemadeWhitelistServiceProperties {

    private String name;

    private String echoPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEchoPath() {
        return echoPath;
    }

    public void setEchoPath(String echoPath) {
        this.echoPath = echoPath;
    }

}
