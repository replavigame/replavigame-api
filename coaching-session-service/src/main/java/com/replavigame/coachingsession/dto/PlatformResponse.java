package com.replavigame.coachingsession.dto;

import lombok.Data;

@Data
public class PlatformResponse {
    private Long id;

    private String name;
    private String url;
    private Long session_id;
}
