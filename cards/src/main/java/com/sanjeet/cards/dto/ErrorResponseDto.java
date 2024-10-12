package com.sanjeet.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Error Response",description = "Error Response Details")
public class ErrorResponseDto {
    @Schema(name = "apiPath",description = "apiPath")
    private String apiPath;
    @Schema(name = "errorCode",description = "errorCode")
    private HttpStatus errorCode;
    @Schema(name = "errorMessage",description = "errorMessage")
    private String errorMessage;
    @Schema(name = "errorTime",description = "error time details")
    private LocalDateTime errorTime;
}
