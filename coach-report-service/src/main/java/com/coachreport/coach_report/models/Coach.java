package com.coachreport.coach_report.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    private Long id;
    private String nameCoach;
    private String name;
    private String lastName;
    private Long gameId;
}
