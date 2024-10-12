package com.sanjeet.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Response",description = "Success Response")
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class ResponseDto {
    @Schema(name = "statusCode",description = "statusCode")
    private String statusCode;
    @Schema(name = "message",description = "message")
    private String message;
}
