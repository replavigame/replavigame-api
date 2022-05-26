package com.replavigame.order_game_coin.dto;

import lombok.Data;

@Data
public class DetailGameCoinResponse {
    private Long quantify;
    private Long gameCoinId;
    private Double subtotal;
}
