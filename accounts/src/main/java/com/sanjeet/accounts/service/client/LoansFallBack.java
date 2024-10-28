package com.sanjeet.accounts.service.client;

import com.sanjeet.accounts.dto.LoanDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoansFeignClient {
    private static final Logger logger = LoggerFactory.getLogger(LoansFallBack.class);
    @Override
    public ResponseEntity<LoanDto> fetchLoan(String correlationId, String mobileNumber) {
        logger.debug("fallback -loans");
        return null;
    }
}
