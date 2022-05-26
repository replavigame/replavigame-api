package com.replavigame.order_game_coin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order_game_coins")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderGameCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "sale_order")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date saleOrder;
}
