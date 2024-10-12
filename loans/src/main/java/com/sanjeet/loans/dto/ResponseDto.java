package com.sanjeet.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Response Details")
public class ResponseDto {
    @Schema(name = "SuccessCode", description = "Status Code")
    private String statusCode;
    @Schema(name = "SuccessMessage", description = "Status Message")
    private String message;
}
