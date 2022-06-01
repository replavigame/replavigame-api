package com.detail_guide.detail_guide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class DetailGuideResponse {

    private Long id;

    private String name;

    @Lob
    private String videoUrl;

    private Long position;

    private Long guideId;
}
