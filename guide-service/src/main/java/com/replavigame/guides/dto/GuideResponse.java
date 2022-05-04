package com.replavigame.guides.dto;

import lombok.Data;

@Data
public class GuideResponse {
    private Long id;

    private String title;

    private String description;

    private Long coachId;

    private CategoryResponse category;

    private Long gameId;

    private Long points;

    private Double descount;
}
