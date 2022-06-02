package com.coachreport.coach_report.models;

import lombok.Data;
import java.util.Date;

@Data
public class Coach {
    private Long id;
    private String nameCoach;
    private String name;
    private String lastName;
    private Long gameId;
}
