package com.sanjeet.accounts.controller;

import com.sanjeet.accounts.constants.AccountsConstants;
import com.sanjeet.accounts.dto.CustomerDetailsDto;
import com.sanjeet.accounts.dto.ErrorResponseDto;
import com.sanjeet.accounts.service.client.ICustomerService;
import com.sanjeet.accounts.service.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/customer")
@Tag(description = "GET Customer Details for Customer in myBank application", name = "Customer Details API")
@Validated
@AllArgsConstructor
public class CustomerController {

    private ICustomerService customerService;

    @Operation(summary = "Fetch details of a customer", description = "Fetch details of a customer along with its loan,card and accounts details using his phone number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam @Valid @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN, message = "Mobile number should be 10 digits") String mobileNumber) {
     CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber);
     return ResponseEntity.ok().body(customerDetailsDto);
    }
}
