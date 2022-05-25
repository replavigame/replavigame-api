package com.replavigame.order_guide.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class OrderGuideResponse {
    private Long id;
    private Long totalPrice;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = Shape.STRING)
    private Date saleCreated;
    private Long coachId;
    private Long customerId;
}
