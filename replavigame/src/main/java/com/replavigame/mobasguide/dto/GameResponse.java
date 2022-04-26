package com.replavigame.mobasguide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class GameResponse {
    private Long id;
    private String name;
    @Lob
    private String description;
}
