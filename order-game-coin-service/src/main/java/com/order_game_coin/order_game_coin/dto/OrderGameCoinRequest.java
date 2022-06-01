package com.order_game_coin.order_game_coin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class OrderGameCoinRequest {
    private Long customerId;
    private Long totalPrice;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = Shape.STRING)
    private Date saleOrder;
}
