package com.coachreport.coach_report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CoachReportRequest {
    private String observation;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = JsonFormat.Shape.STRING)
    private Date receivedDate;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = JsonFormat.Shape.STRING)
    private Date qualifiedDate;
    private Boolean approved;
}
