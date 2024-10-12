package com.sanjeet.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "Error Response",description = "Error Response")
public class ErrorResponseDto {
    @Schema (description = "api path from where the error is generated")
    private String apiPath;
    @Schema(description = "error code")
    private HttpStatus errorCode;
    @Schema(description = "error message")
    private String errorMessage;
    @Schema(description = "error occurance time")
    private LocalDateTime errorTime;
}
