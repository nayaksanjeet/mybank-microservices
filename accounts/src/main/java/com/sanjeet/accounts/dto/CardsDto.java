package com.sanjeet.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Schema(name = "Cards",description = "Cards details")
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class CardsDto {
    @Schema(name = "mobileNumber",description = "mobileNumber")
    @Pattern(regexp = "^[6-9][0-9]{9}$",message = "Invalid mobile number")
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;
}
