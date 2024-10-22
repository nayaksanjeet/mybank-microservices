package com.sanjeet.accounts.service.client;

import com.sanjeet.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="loans")
public interface LoansFeignClient {

    @GetMapping(value ="api/v1/loans/fetch", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoanDto> fetchLoan(@RequestParam String mobileNumber);

}
