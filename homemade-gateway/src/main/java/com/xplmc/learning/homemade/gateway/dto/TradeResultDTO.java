package com.xplmc.learning.homemade.gateway.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringJoiner;

/**
 * trade response DTO
 *
 * @author luke
 */
public class TradeResultDTO implements Serializable {

    /**
     * system group
     */
    private String systemGroup;

    /**
     * system code
     */
    private String systemCode;

    /**
     * system name
     */
    private String systemName;

    /**
     * API's signature
     */
    private String apiSignature;

    /**
     * API's name
     */
    private String apiName;

    /**
     * return response
     */
    private Object response;

    /**
     * request time;
     */
    private Timestamp requestTime;

    /**
     * response time
     */
    private Timestamp responseTime;

    /**
     * cost time
     */
    private Long costTime;

    public String getSystemGroup() {
        return systemGroup;
    }

    public void setSystemGroup(String systemGroup) {
        this.systemGroup = systemGroup;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getApiSignature() {
        return apiSignature;
    }

    public void setApiSignature(String apiSignature) {
        this.apiSignature = apiSignature;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", TradeResultDTO.class.getSimpleName() + "[", "]")
                .add("systemGroup='" + systemGroup + "'")
                .add("systemCode='" + systemCode + "'")
                .add("systemName='" + systemName + "'")
                .add("apiSignature='" + apiSignature + "'")
                .add("apiName='" + apiName + "'")
                .add("response=" + response)
                .add("requestTime=" + requestTime)
                .add("responseTime=" + responseTime)
                .add("costTime=" + costTime)
                .toString();
    }

}
