package com.coachingsession.coaching_session.client;

import com.coachingsession.coaching_session.models.Coach;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CoachHystrixFallbackFactory implements CoachClient {
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
