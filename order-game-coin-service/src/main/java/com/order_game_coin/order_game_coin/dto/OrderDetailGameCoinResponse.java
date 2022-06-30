package com.order_game_coin.order_game_coin.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailGameCoinResponse {
    List<DetailGameCoinResponse> lCoinResponses;
    private Long userId;
    private OrderGameCoinResponse orderGameCoinResponse;
    private Double total;
}
