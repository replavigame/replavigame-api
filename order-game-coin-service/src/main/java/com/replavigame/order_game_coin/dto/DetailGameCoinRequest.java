package com.replavigame.order_game_coin.dto;

import lombok.Data;

@Data
public class DetailGameCoinRequest {
    private Long gameCoinId;
    private Long quantify;
    private Double priceGameCoin;
}
