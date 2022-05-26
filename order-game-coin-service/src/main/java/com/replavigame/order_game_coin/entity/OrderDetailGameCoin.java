package com.replavigame.order_game_coin.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order_detail_game_coins")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailGameCoin {

    @EmbeddedId
    private OrderDetailGameCoinId orderDetailGameCoinId = new OrderDetailGameCoinId();

    private Long quantify;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_game_id")
    private OrderGameCoin orderGameCoin;

    @Column(name = "subtotal")
    private Double subtotal;
}
