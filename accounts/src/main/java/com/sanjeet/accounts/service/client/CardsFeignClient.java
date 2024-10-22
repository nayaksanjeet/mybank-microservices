package com.sanjeet.accounts.service.client;

import com.sanjeet.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value ="api/v1/cards/fetch", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);
}
