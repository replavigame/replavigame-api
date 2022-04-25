package com.replavigame.mobasguide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class MobaGuideRequest {
    @Lob
    private String description;

    private Long heroId;
    private Long roleId;
    private Long gameId;
    private Long expertId;
    private Double price;
}
