package com.xplmc.learning.homemade.gateway.service;

import java.util.Map;

/**
 * echo server interface
 *
 * @author luke
 */
public interface EchoService {

    /**
     * simple echo method
     *
     * @param text echo request text
     * @return echo response
     */
    Map<String, String> echo(String text);

}
