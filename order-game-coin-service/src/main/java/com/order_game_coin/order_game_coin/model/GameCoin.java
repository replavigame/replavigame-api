package com.order_game_coin.order_game_coin.model;

import lombok.Data;

@Data
public class GameCoin {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean avaible;
}
