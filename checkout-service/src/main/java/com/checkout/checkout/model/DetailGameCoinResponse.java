package com.checkout.checkout.model;

import lombok.Data;

@Data
public class DetailGameCoinResponse {
    private Long quantify;
    private Long gameCoinId;
    private Double subtotal;
    private GameCoin gameCoin;
}
