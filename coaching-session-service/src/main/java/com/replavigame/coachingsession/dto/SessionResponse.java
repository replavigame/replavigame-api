package com.replavigame.coachingsession.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SessionResponse {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Boolean available;

}
