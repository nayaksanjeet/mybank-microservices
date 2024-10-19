package com.sanjeet.accounts.controller;

import com.sanjeet.accounts.constants.AccountsConstants;
import com.sanjeet.accounts.dto.AccountsContactDto;
import com.sanjeet.accounts.dto.CustomerDto;
import com.sanjeet.accounts.dto.ErrorResponseDto;
import com.sanjeet.accounts.dto.RsponseDto;
import com.sanjeet.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

@RestController
@RequestMapping(path = "api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})

@Validated
@Tag(
        name = "CRUD Rest API for Accounts in My Bank",
        description = "Operations pertaining to Accounts(Create,FETCH,UPDATE and DELETE) in My Bank"
)
public class AccountsController {

    private IAccountsService accountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactDto accountsContactDto;

    public AccountsController(IAccountsService accountsService) {
        this.accountsService = accountsService;

    }

    @Operation(
            summary = "Open a new Account associated with a customer",
            description = "Create a new Account associated with a customer")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(
                    responseCode = "500", description = "HTTP status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
    })

    @PostMapping(value = "/create")
    public ResponseEntity<RsponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new RsponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch details of a customer",
            description = "Fetch details of a customer using his phone number")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam
                                                            @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN, message = "Mobile number should be 10 digits")
                                                            String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).
                body(customerDto);
    }


    @Operation(
            summary = "Update Account detail of the customer in My Bank",
            description = "Update Account details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "417", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(
                    responseCode = "500", description = "HTTP Status Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)))


    })

    @PutMapping("/update")
    public ResponseEntity<RsponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).
                    body(new RsponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new RsponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }


    @Operation(
            summary = "Delete Account detail of the customer in My Bank",
            description = "Delete Account details of a customer using his mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "HTTP Status  OK"),
            @ApiResponse(
                    responseCode = "417", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(
                    responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)))

    })
    @DeleteMapping("/delete")
    public ResponseEntity<RsponseDto> deleteAccount(@RequestParam
                                                    @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN, message = "Mobile number should be 10 digits") String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).
                    body(new RsponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new RsponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }

    @Operation(summary = "Get build version", description = "Get build version that is deployed into accounts  microservice")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity.ok().body(buildVersion);
    }

    @Operation(summary = "Get Java version", description = "Get Java version that is installed in the environment of accounts  microservice")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(summary = "Get contact details", description = "Get contact details of accounts microservice in case of any issues")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500", description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(

            ))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactDto> getContactInfo(){
        return ResponseEntity.ok().body(accountsContactDto);
    }
}
