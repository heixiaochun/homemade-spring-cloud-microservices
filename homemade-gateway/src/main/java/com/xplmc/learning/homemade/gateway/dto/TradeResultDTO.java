package com.xplmc.learning.homemade.gateway.dto;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * trade result DTO
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
     * API's url
     */
    private String apiUrl;

    /**
     * API's name
     */
    private String apiName;

    /**
     * return code
     */
    private String retCode;

    /**
     * return msg
     */
    private String retMsg;

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

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
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
        return MoreObjects.toStringHelper(this)
                .add("systemGroup", systemGroup)
                .add("systemCode", systemCode)
                .add("systemName", systemName)
                .add("apiUrl", apiUrl)
                .add("apiName", apiName)
                .add("retCode", retCode)
                .add("retMsg", retMsg)
                .add("requestTime", requestTime)
                .add("responseTime", responseTime)
                .add("costTime", costTime)
                .toString();
    }

}
