package com.coachreport.coach_report.dto;

import lombok.Data;

@Data
public class CoachDocumentResponse {
    private Long id;
    private String title;
    private String documentUrl;
    private String comment;
    private Long coachreport_id;
}
