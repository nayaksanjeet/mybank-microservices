package com.sanjeet.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name ="response",description = "response of API")
public class RsponseDto {
    @Schema(description = "response code")
    private String statusCode;
    @Schema(description = "response message")
    private String message;
}
