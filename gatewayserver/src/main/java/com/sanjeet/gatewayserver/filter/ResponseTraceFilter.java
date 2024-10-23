package com.sanjeet.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ResponseTraceFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postResponseFilter() {
        return (exchange,chain) ->{
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders reqHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(reqHeaders);
                logger.debug("myBank-correlation-id found in ResponseTraceFilter: {}", correlationId);
                exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
            }));
        };
    }
}
