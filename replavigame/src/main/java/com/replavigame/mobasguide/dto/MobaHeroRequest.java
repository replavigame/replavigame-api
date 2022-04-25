package com.replavigame.mobasguide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class MobaHeroRequest {
    @Lob
    private String image;

    private String name;
}
