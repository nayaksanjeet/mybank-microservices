package com.sanjeet.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorResponse", description = "Error Response Details")
public class ErrorResponseDto {
    @Schema(name = "apiPath", description = "Error origin API Path")
    private String apiPath;
    @Schema(name = "errorCode", description = "Error Code")
    private HttpStatus errorCode;
    @Schema(name = "errorMessage", description = "Error Message")
    private String errorMessage;
    @Schema(name = "errorTime", description = "Error Time")
    private LocalDateTime errorTime;
}
