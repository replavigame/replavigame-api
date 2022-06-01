package com.order_guide.order_guide.dto;

import com.order_guide.order_guide.model.Coach;
import com.order_guide.order_guide.model.Guide;

import lombok.Data;

@Data
public class DetailResponse {
    private Long orderId;
    private Long guideId;
    private Guide guide;
    private Coach coach;
    private Long price;
}
