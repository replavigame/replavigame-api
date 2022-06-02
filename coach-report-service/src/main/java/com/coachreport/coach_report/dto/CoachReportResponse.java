package com.coachreport.coach_report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CoachReportResponse {
    private Long id;
    private String observation;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = JsonFormat.Shape.STRING)
    private Date receivedAt;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", shape = JsonFormat.Shape.STRING)
    private Date qualifiedAt;
    private Boolean approved;
    private List<CoachDocumentResponse> coachDocuments;
}
