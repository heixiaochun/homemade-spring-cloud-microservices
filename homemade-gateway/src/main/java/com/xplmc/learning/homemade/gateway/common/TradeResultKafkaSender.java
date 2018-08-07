package com.xplmc.learning.homemade.gateway.common;

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

    private KafkaTemplate<String, TradeResultDTO> kafkaTemplate;

    @Value("${tradeResult.topicName}")
    private String tradeResultTopicName;

    @Value("${tradeResult.systemGroup}")
    private String tradeResultSystemGroup;

    @Value("${tradeResult.systemCode}")
    private String tradeResultSystemCode;

    @Value("${tradeResult.systemName}")
    private String tradeResultSystemName;

    @Autowired
    public TradeResultKafkaSender(KafkaTemplate<String, TradeResultDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * send tradeResultDTO to kafka
     */
    public void send(String retCode, String retMsg) {
        TradeResultDTO tradeResultDTO = this.buildTradeResultDTO(retCode, retMsg);
        ListenableFuture<SendResult<String, TradeResultDTO>> future = kafkaTemplate.send(tradeResultTopicName, tradeResultDTO);
        future.addCallback(o -> logger.info("msg send success: {}", o.getProducerRecord()),
                throwable -> logger.error("msg send failure", throwable));
    }

    /**
     * init tradeResultDTO
     */
    private TradeResultDTO buildTradeResultDTO(String retCode, String retMsg) {
        TradeResultDTO tradeResultDTO = new TradeResultDTO();
        tradeResultDTO.setSystemGroup(tradeResultSystemGroup);
        tradeResultDTO.setSystemCode(tradeResultSystemCode);
        tradeResultDTO.setSystemName(tradeResultSystemName);
        tradeResultDTO.setRetCode(retCode);
        tradeResultDTO.setRetMsg(retMsg);
        return tradeResultDTO;
    }

}
