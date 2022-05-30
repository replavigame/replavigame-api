package com.replavigame.coachingsession.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SessionRequest {
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date endDate;
    private Boolean available;
}
