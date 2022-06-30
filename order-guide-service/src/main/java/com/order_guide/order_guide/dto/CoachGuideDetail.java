package com.order_guide.order_guide.dto;

import com.order_guide.order_guide.model.Guide;

import lombok.Data;

@Data
public class CoachGuideDetail {
    private Long guideId;
    private Guide guide;
    private Long quantify;    
}
