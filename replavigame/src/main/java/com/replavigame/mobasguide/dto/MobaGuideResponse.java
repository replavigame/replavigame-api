package com.replavigame.mobasguide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class MobaGuideResponse {
    private Long id;

    @Lob
    private String description;

    private MobaHeroResponse hero;

    private MobaRoleResponse role;

    private GameResponse game;

    private Double price;
}
