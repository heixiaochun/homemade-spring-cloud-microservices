package com.xplmc.learning.homemade.gateway.common.log;

import com.xplmc.learning.homemade.gateway.common.constant.LogConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * log http request and response
 *
 * @author luke
 */
@Aspect
@Component
public class RequestResponseLogAdvice {

    private static final Logger requestResponseLogger = org.slf4j.LoggerFactory.getLogger(LogConstants.LOGGER_REQUEST_RESPONSE_INFO);

    private TradeResultKafkaSender tradeResultKafkaSender = null;

    @Autowired
    public RequestResponseLogAdvice(TradeResultKafkaSender tradeResultKafkaSender) {
        this.tradeResultKafkaSender = tradeResultKafkaSender;
    }

    /**
     * logging http request and response
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
}
