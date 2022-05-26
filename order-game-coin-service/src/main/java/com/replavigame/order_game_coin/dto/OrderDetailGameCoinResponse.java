package com.replavigame.order_game_coin.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailGameCoinResponse {
    List<DetailGameCoinResponse> lCoinResponses;
    private Long customerId;
    private OrderGameCoinResponse orderGameCoinResponse;
}
