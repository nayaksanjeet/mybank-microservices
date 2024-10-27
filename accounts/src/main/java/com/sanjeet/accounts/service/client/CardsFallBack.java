package com.sanjeet.accounts.service.client;

import com.sanjeet.accounts.dto.CardsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient{
    private static final Logger logger = LoggerFactory.getLogger(CardsFallBack.class);
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        logger.debug("fallback -cards");
        return null;
    }
}
