package com.replavigame.coachingsession.dto;

import lombok.Data;

@Data
public class PlatformRequest {
    private String name;
    private String url;
    private Long session_id;
}
