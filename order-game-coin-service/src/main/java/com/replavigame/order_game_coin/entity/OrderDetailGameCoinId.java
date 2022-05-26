package com.replavigame.order_game_coin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class OrderDetailGameCoinId implements Serializable {
    private Long orderId;

    @Column(name = "game_coin_id")
    private Long gameCoinId;
}
