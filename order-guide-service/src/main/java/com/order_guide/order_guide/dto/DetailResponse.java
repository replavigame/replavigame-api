package com.order_guide.order_guide.dto;

import lombok.Data;

@Data
public class DetailResponse {
    private Long orderId;
    private Long guideId;
    private Long price;
}
