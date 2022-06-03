package com.coachingsession.coaching_session.client;

import com.coachingsession.coaching_session.models.Coach;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coach-service",fallback = CoachHystrixFallbackFactory.class)
public interface CoachClient {

    @GetMapping("/coaches/{id}")
    ResponseEntity<Coach> geById(@PathVariable("id") Long id);
}
