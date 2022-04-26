package com.replavigame.mobasguide.dto;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class GameRequest {
    private String name;
    @Lob
    private String description;
}
