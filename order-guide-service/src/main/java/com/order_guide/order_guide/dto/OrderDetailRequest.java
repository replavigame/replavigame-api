package com.order_guide.order_guide.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private Long userId;
    private List<GuideDetailGuideRequest> detail;
}
