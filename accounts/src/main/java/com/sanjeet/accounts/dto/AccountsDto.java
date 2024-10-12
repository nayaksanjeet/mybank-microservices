package com.sanjeet.accounts.dto;

import com.sanjeet.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Accounts", description = "Account Details")
public class AccountsDto {
    @NotNull(message = "Account Type can not be empty")
    @Schema(description = "Account Type",example = "Savings")
    private String accountType;

    @Schema(name = "branch address",description = "branch address",example = "123 Main Street, New York")
    @NotNull(message = "BranchAddress can not be empty")
    private String branchAddress;

    @Schema(description = "Account Number",example = "1234567890")
    @NotNull(message = "AccountNumber can not be empty")
    @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN, message = "AccountNumber should be 10 digits")
    private Long accountNumber;
}
