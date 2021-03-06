package com.xplmc.learning.homemade.whitelist.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.StringJoiner;

/**
 * mock response properties
 *
 * @author luke
 */
@ConfigurationProperties(prefix = "mock.response")
public class MockResponseProperties {

    /**
     * randomly return error 500
     */
    private boolean randomInternalError = false;

    /**
     * chance of error 500
     */
    private double internalErrorChance = 0.0;

    public boolean isRandomInternalError() {
        return randomInternalError;
    }

    public void setRandomInternalError(boolean randomInternalError) {
        this.randomInternalError = randomInternalError;
    }

    public double getInternalErrorChance() {
        return internalErrorChance;
    }

    public void setInternalErrorChance(double internalErrorChance) {
        this.internalErrorChance = internalErrorChance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MockResponseProperties.class.getSimpleName() + "[", "]")
                .add("randomInternalError=" + randomInternalError)
                .add("internalErrorChance=" + internalErrorChance)
                .toString();
    }
}
