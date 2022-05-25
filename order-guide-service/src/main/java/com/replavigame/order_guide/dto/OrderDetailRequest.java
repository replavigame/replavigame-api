package com.replavigame.order_guide.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailRequest {
    private Long customerId;
    private Long coachId;
    private List<GuideDetailGuideRequest> detail;
}
