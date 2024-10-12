package com.sanjeet.accounts.dto;

import com.sanjeet.accounts.constants.AccountsConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    @NotNull(message = "AccountType can not be empty")
    private String accountType;
    @NotNull(message = "BranchAddress can not be empty")
    private String branchAddress;
    @NotNull(message = "AccountNumber can not be empty")
    @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN, message = "AccountNumber should be 10 digits")
    private Long accountNumber;
}
