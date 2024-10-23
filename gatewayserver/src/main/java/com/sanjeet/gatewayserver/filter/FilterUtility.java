package com.sanjeet.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {
    public static final String CORRELATION_ID = "myBank-correlation-id";


    public String getCorrelationId(HttpHeaders requestHeaders) {
        if(requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeadersList = requestHeaders.get(CORRELATION_ID);
            return requestHeadersList.stream().findFirst().get();
        }
        return null;
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setRequestHeaders(exchange, CORRELATION_ID, correlationId);
    }
    private ServerWebExchange setRequestHeaders(ServerWebExchange exchange, String key,String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(key, value).build()).build();
    }
}
