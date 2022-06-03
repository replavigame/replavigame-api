package com.order_guide.order_guide.dto;

import java.util.List;

import com.order_guide.order_guide.model.Coach;

import lombok.Data;

@Data
public class CoachOrderDetailResponse {
    private List<CoachGuideDetail> guideDetails;
    private Long coachId;
    private Coach coach;
    private Long sales;
}
