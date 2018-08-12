package com.xplmc.learning.homemade.gateway.common.log;

import com.alibaba.fastjson.JSON;
import com.xplmc.learning.homemade.gateway.common.property.TradeResultProperties;
import com.xplmc.learning.homemade.gateway.dto.TradeResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * send trade result to kafka
 *
 * @author luke
 */
@Component
@EnableConfigurationProperties({TradeResultProperties.class})
public class TradeResultKafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(TradeResultKafkaSender.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    private TradeResultProperties tradeResultProperties;

    @Autowired
    TradeResultKafkaSender(KafkaTemplate<String, String> kafkaTemplate, TradeResultProperties tradeResultProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.tradeResultProperties = tradeResultProperties;
    }

    /**
     * send tradeResultDTO to kafka
     */
    void send(TradeResultDTO tradeResultDTO) {
        if (tradeResultProperties.isSendToKafka()) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(tradeResultProperties.getTopicName(),
                    JSON.toJSONString(tradeResultDTO));
            future.addCallback(o -> logger.info("msg send success: {}", o.getProducerRecord()),
                    throwable -> logger.error("msg send failure", throwable));
        }
    }

    /**
     * init tradeResultDTO
     */
    TradeResultDTO initTradeResultDTO() {
        TradeResultDTO tradeResultDTO = new TradeResultDTO();
        tradeResultDTO.setSystemGroup(tradeResultProperties.getSystemGroup());
        tradeResultDTO.setSystemCode(tradeResultProperties.getSystemCode());
        tradeResultDTO.setSystemName(tradeResultProperties.getSystemName());
        return tradeResultDTO;
    }

}
