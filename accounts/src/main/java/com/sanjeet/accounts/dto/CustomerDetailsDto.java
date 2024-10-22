package com.sanjeet.accounts.dto;

import com.sanjeet.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name="CustomerDetails",description = "Customer Details including account,loan and card details")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class CustomerDetailsDto {

    @NotNull
    @Size(min = 5,max = 30 ,message = "Name should be between 5 and 30 characters")
    @Schema(description = "Customer Name",example = "John Doe")
    private String name;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email",example = "p7XUk@example.com")
    private String email;

    @NotNull
    @Schema(description = "Mobile Number",example = "1234567890")
    @Pattern(regexp = AccountsConstants.MOBILE_NUMBER_PATTERN,message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(description = "Accounts Details of customer")
    private AccountsDto accountsDto;
    @Schema(description = "LOans Details of the customer")
    private LoanDto loanDto;
    @Schema(description = "Cards Details of the customer")
    private CardsDto cardsDto;
}
