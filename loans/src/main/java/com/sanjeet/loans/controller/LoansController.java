package com.sanjeet.loans.controller;

import com.sanjeet.loans.constants.LoansConstants;
import com.sanjeet.loans.dto.ErrorResponseDto;
import com.sanjeet.loans.dto.LoanDto;
import com.sanjeet.loans.dto.ResponseDto;
import com.sanjeet.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/loans", produces = "application/json")
@AllArgsConstructor
@Validated
@Tag(name = "Loans", description = "APIs to perform CRUD Operation (create ,fetch ,update and delete) on a loan")
public class LoansController {

    private ILoanService loanService;

    @Operation(summary = "Create a new loan",
            description = "This API is used to create a new loan using mobileNumber. ")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Loan created successfully"),
            @ApiResponse(responseCode = "417", description = "Loan creation failed. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = LoansConstants.MOBILE_NUMBER_PATTERN, message = "mobile number should be 10 digits") String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch loan details", description = "This API is used to fetch loan details using mobileNumber. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Loan details Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoan(@RequestParam @Pattern(regexp = LoansConstants.MOBILE_NUMBER_PATTERN, message = "mobile number should be 10 digits") String mobileNumber) {
        LoanDto loanDetail = loanService.fechLoanDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDetail);
    }
}
