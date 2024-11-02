package com.sanjeet.accounts.functions;

import com.sanjeet.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private final static Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService) {
        return accountNumber ->{
            logger.info("Updating communication status for account number: {}", accountNumber);
            accountsService.updateCommunicationStatus(accountNumber);
        };
    }
}
