package com.xplmc.learning.homemade.gateway.common.log;

import com.alibaba.fastjson.JSON;
import com.xplmc.learning.homemade.gateway.dto.TradeResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TradeResultKafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(TradeResultKafkaSender.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${tradeResult.topicName}")
    private String tradeResultTopicName;

    @Value("${tradeResult.systemGroup}")
    private String tradeResultSystemGroup;

    @Value("${tradeResult.systemCode}")
    private String tradeResultSystemCode;

    @Value("${tradeResult.systemName}")
    private String tradeResultSystemName;

    @Autowired
    TradeResultKafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * send tradeResultDTO to kafka
     */
    void send(TradeResultDTO tradeResultDTO) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(tradeResultTopicName, JSON.toJSONString(tradeResultDTO));
        future.addCallback(o -> logger.info("msg send success: {}", o.getProducerRecord()),
                throwable -> logger.error("msg send failure", throwable));
    }

    /**
     * init tradeResultDTO
     */
    TradeResultDTO initTradeResultDTO() {
        TradeResultDTO tradeResultDTO = new TradeResultDTO();
        tradeResultDTO.setSystemGroup(tradeResultSystemGroup);
        tradeResultDTO.setSystemCode(tradeResultSystemCode);
        tradeResultDTO.setSystemName(tradeResultSystemName);
        return tradeResultDTO;
    }

}
