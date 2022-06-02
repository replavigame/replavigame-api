package com.coachreport.coach_report.client;

import com.coachreport.coach_report.models.Coach;
import org.springframework.http.ResponseEntity;

public class CoachHystrixFallbackFactory implements CoachClient{
    @Override
    public ResponseEntity<Coach> geById(Long id) {
        Coach coach = Coach.builder()
                .name("unknown")
                .lastName("unknown")
                .gameId(0L)
                .nameCoach("unknown").build();
        return ResponseEntity.ok(coach);

    }
}
