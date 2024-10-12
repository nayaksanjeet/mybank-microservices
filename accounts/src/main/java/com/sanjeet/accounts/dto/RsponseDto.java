package com.sanjeet.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RsponseDto {
    private String statusCode;
    private String message;
}
