package com.sanjeet.accounts.controller;

import com.sanjeet.accounts.constants.AccountsConstants;
import com.sanjeet.accounts.dto.CustomerDetailsDto;
import com.sanjeet.accounts.dto.ErrorResponseDto;
import com.sanjeet.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Tag(description = "GET Customer Details for Customer in myBank application", name = "Customer Details API")
@Validated
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }
    @Operation(summary = "Fetch details of a customer", description = "Fetch details of a customer along with its loan,card and accounts details using his phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping(value = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("myBank-correlation-id") String correlationId,
            @RequestParam @Valid @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN, message = "Mobile number should be 10 digits") String mobileNumber) {
     //logger.debug("correlationId found: {}",correlationId);
        logger.debug("fetchCustomerDetails is invoked");
        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(correlationId,mobileNumber);
        logger.debug("fetchCustomerDetails is completed");
     return ResponseEntity.ok().body(customerDetailsDto);
    }
}
