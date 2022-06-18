package com.order_game_coin.order_game_coin.dto;

import com.order_game_coin.order_game_coin.model.GameCoin;

import lombok.Data;

@Data
public class DetailGameCoinResponse {
    private Long quantify;
    private Long gameCoinId;
    private Double subtotal;
    private GameCoin gameCoin;
}
