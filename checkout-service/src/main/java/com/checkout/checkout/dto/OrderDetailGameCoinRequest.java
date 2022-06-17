package com.checkout.checkout.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class OrderDetailGameCoinRequest {
    List<DetailGameCoinRequest> lCoinRequests;
    private Long userId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = Shape.STRING)
    private Date saleOrder;
}
