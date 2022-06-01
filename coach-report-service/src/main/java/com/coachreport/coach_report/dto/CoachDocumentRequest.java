package com.coachreport.coach_report.dto;

import lombok.Data;

@Data
public class CoachDocumentRequest {
    private String title;
    private String documentUrl;
    private String comment;
}
