package com.xplmc.learning.homemade.gateway.common.log;

import com.xplmc.learning.homemade.gateway.dto.TradeResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * intercept RestTemplate's invoking result
 *
 * @author luke
 */
@Component
public class TradeResultClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private TradeResultKafkaSender tradeResultKafkaSender = null;

    @Autowired
    public TradeResultClientHttpRequestInterceptor(TradeResultKafkaSender tradeResultKafkaSender) {
        this.tradeResultKafkaSender = tradeResultKafkaSender;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long start = System.currentTimeMillis();
        ClientHttpResponse response = null;
        try {
            response = execution.execute(request, body);
            return response;
        } finally {
            sendTradeResultToKafka(request, response, start);
        }
    }

    /**
     * send trade result to kafka
     * only when result is a HashMap
     */
    private void sendTradeResultToKafka(HttpRequest request, ClientHttpResponse response, long start) {
        TradeResultDTO tradeResultDTO = tradeResultKafkaSender.initTradeResultDTO();
        tradeResultDTO.setApiSignature(request.getURI().toString());
        tradeResultDTO.setRequestTime(new Timestamp(start));
        tradeResultDTO.setResponseTime(new Timestamp(System.currentTimeMillis()));
        tradeResultDTO.setCostTime(System.currentTimeMillis() - start);
        tradeResultDTO.setResponse(response);
        tradeResultKafkaSender.send(tradeResultDTO);
    }

}
