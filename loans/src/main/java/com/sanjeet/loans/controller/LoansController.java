package com.sanjeet.loans.controller;

import com.sanjeet.loans.constants.LoansConstants;
import com.sanjeet.loans.dto.ErrorResponseDto;
import com.sanjeet.loans.dto.LoanDto;
import com.sanjeet.loans.dto.LoansContactInfoDto;
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
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/loans", produces = "application/json")
@Validated
@Tag(name = "Loans", description = "APIs to perform CRUD Operation (create ,fetch ,update and delete) on a loan")
public class LoansController {

    private ILoanService loanService;

    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    Environment environment;
    @Autowired
    private LoansContactInfoDto loansContactInfoDto;
    public LoansController(ILoanService loanService) {
        this.loanService = loanService;
    }
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

    @Operation(summary = "Update loan details", description = "This API is used to update loan details . ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details updated successfully"),
            @ApiResponse(responseCode = "417", description = "Loan details Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoanDto loanDto) {
        boolean isLoanUpdated = loanService.updateLoan(loanDto);
        if (isLoanUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Close loan details", description = "This API is used to delete loan details using mobileNumber. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Loan details deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Loan details Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @DeleteMapping("/remove")
    public ResponseEntity<ResponseDto> closeLoan(@RequestParam @Pattern(regexp = LoansConstants.MOBILE_NUMBER_PATTERN, message = "mobile number should be 10 digits") String mobileNumber) {
        boolean isDeleted = loanService.removeLoan(mobileNumber);
        if(isDeleted)
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        else
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
    }

    @Operation(summary = "Get build version", description = "This API is used to get build version of Loans microservice. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Build version fetched successfully"),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity.ok().body(buildVersion);
    }

    @Operation(summary = "Get contact info", description = "This API is used to get contact info of Loans microservice in case of any issues. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact info fetched successfully"),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity.ok().body(loansContactInfoDto);
    }

    @Operation(summary = "Get java version", description = "This API is used to get java version of Loans microservice. ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Java version fetched successfully"),
            @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }
}
