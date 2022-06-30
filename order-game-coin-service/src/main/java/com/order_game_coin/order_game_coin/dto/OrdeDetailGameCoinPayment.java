package com.order_game_coin.order_game_coin.dto;

import java.util.List;

import com.order_game_coin.order_game_coin.model.User;

import lombok.Data;

@Data
public class OrdeDetailGameCoinPayment {
    List<DetailGameCoinResponse> lCoinResponses;
    private Long userId;
    private OrderGameCoinResponse orderGameCoinResponse;
    private Double total;
    private User user;
}
