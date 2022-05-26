package com.replavigame.order_game_coin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderGameCoinResponse {
    private Long id;
    private Long customerId;
    private Long totalPrice;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date saleOrder;
}