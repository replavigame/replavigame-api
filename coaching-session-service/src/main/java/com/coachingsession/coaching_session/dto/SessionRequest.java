package com.coachingsession.coaching_session.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SessionRequest {
    private String name;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm",shape = JsonFormat.Shape.STRING)
    private Date startDate;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = JsonFormat.Shape.STRING)
    private Date endDate;
    private Boolean available;
    private Long coachId;
    private Long userId;
}
