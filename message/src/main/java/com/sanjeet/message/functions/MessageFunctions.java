package com.sanjeet.message.functions;

import com.sanjeet.message.dto.AccountsMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    public static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMessageDto,AccountsMessageDto> email(){
      return   accountsMessageDto -> {
          logger.info("sending email with Details : " + accountsMessageDto.toString());
          return accountsMessageDto;
      };

    }

    @Bean
    public Function<AccountsMessageDto,Long> sms(){
        return accountsMessageDto -> {
            logger.info("sending sms with Details : " + accountsMessageDto.toString());
            return accountsMessageDto.accountNumber();
        };
    }
}
