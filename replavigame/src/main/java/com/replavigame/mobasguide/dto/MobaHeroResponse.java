package com.replavigame.mobasguide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class MobaHeroResponse {
    private Long id;

    @Lob
    private String image;

    private String name;
}
