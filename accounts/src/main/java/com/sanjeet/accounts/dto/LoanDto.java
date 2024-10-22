package com.sanjeet.accounts.dto;

import com.sanjeet.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Loans", description = "Loan Details")
public class LoanDto {
    @Schema(name = "mobileNumber", description = "Mobile Number", example = "9876543210")
    @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN,message = "mobile number should be 10 digits")
    private String mobileNumber;
    @Schema(name = "loanNumber", description = "Loan Number", example = "123456789067")
    private String loanNumber;
    @Schema(name = "loanType", description = "Loan Type", example = "Home Loan")
    private String loanType;
    @Schema(name = "totalLoan", description = "Total Loan Amount", example = "100000")
    private Integer totalLoan;
    @Schema(name = "amountPaid", description = "Amount Already Paid", example = "50000")
    private Integer amountPaid;
    @Schema(name = "outstandingAmount", description = "Outstanding Amount", example = "50000")
    private Integer outstandingAmount;
}
