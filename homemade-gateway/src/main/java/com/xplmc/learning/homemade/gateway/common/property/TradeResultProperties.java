package com.xplmc.learning.homemade.gateway.common.property;

import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * trade result properties
 *
 * @author luke
 */
@ConfigurationProperties(prefix = "tradeResult")
public class TradeResultProperties {

    /**
     * send trade result to kafka
     * default false
     */
    private boolean sendToKafka = false;

    /**
     * trade result kafka topic name
     */
    private String topicName = null;

    /**
     * trade result system group
     */
    private String systemGroup = null;

    /**
     * trade result system code
     */
    private String systemCode = null;

    /**
     * trade result system name
     */
    private String systemName = null;

    public boolean isSendToKafka() {
        return sendToKafka;
    }

    public void setSendToKafka(boolean sendToKafka) {
        this.sendToKafka = sendToKafka;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sendToKafka", sendToKafka)
                .add("topicName", topicName)
                .add("systemGroup", systemGroup)
                .add("systemCode", systemCode)
                .add("systemName", systemName)
                .toString();
    }

}
