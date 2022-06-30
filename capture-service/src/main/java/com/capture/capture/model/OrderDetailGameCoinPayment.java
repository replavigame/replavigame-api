package com.capture.capture.model;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailGameCoinPayment {
    private List<DetailGameCoinResponse> lCoinResponses;
    private Long userId;
    private OrderGameCoinResponse orderGameCoinResponse;
    private Double total;
    private User user;
}