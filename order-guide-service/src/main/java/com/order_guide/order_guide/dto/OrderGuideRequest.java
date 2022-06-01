package com.order_guide.order_guide.dto;

import lombok.Data;

@Data
public class OrderGuideRequest {
    private Long totalPrice;
    private Long coachId;
    private Long customerId;
}
