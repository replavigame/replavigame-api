package com.replavigame.detail_guide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class DetailGuideRequest {
    private String name;

    @Lob
    private String videoUrl;

    private Long position;

    private Long guideId;
}
