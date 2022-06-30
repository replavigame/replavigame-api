package com.coachreport.coach_report.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coach {
    private Long id;
    private String nameCoach;
    private String name;
    private String lastName;
    private Long gameId;
}
