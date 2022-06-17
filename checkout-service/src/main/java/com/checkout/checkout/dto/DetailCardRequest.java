package com.checkout.checkout.dto;

import lombok.Data;

@Data
public class DetailCardRequest {
    private Long month;
    private Long day;
    private String cardholder;
    private Long userId;
    private Long obfuscatedCard;
    private Long code;
}
