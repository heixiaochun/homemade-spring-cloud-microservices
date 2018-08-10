package com.xplmc.learning.homemade.gateway.common.log;

import com.xplmc.learning.homemade.gateway.common.constant.LogConstants;
import com.xplmc.learning.homemade.gateway.dto.TradeResultDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * send trade result to kafka
 *
 * @author luke
 */
@Aspect
@Component
public class TradeResultLogAdvice {

    private static final Logger requestResponseLogger = org.slf4j.LoggerFactory.getLogger(LogConstants.LOGGER_REQUEST_RESPONSE_INFO);

    private TradeResultKafkaSender tradeResultKafkaSender = null;

    @Autowired
    public TradeResultLogAdvice(TradeResultKafkaSender tradeResultKafkaSender) {
        this.tradeResultKafkaSender = tradeResultKafkaSender;
    }

    /**
     * logging request url, queryString, requestBody, etc.
     */
    @Around("com.xplmc.learning.homemade.gateway.common.advice.CommonPointCutConfig.restControllers()")
    public Object loggingRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // log request body
        requestResponseLogger.info("request body: {}", joinPoint.getArgs());

        boolean success = true;
        try {
            Object response = joinPoint.proceed();
            // log response body
            requestResponseLogger.info("response body: {}", response);
            // send response to kafka
            sendTradeResultToKafka(joinPoint, response, start);
            return response;
        } catch (Throwable t) {
            success = false;
            throw t;
        } finally {
            // log time cost
            requestResponseLogger.info("signature:{}, success: {}, cost: {}", joinPoint.getSignature().toShortString(),
                    success, System.currentTimeMillis() - start);
        }
    }

    /**
     * send trade result to kafka
     * only when result is a HashMap
     */
    private void sendTradeResultToKafka(ProceedingJoinPoint joinPoint, Object response, long start) {
        TradeResultDTO tradeResultDTO = tradeResultKafkaSender.initTradeResultDTO();
        tradeResultDTO.setApiSignature(joinPoint.getSignature().toShortString());
        tradeResultDTO.setRequestTime(new Timestamp(start));
        tradeResultDTO.setResponseTime(new Timestamp(System.currentTimeMillis()));
        tradeResultDTO.setCostTime(System.currentTimeMillis() - start);
        tradeResultDTO.setResponse(response);
        tradeResultKafkaSender.send(tradeResultDTO);
    }

}
