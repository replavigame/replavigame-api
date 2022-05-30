package com.order_game_coin.order_game_coin.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class OrderDetailGameCoinRequest {
    List<DetailGameCoinRequest> lCoinRequests;
    private Long customerId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = Shape.STRING)
    private Date saleOrder;
    private Long total;
}
