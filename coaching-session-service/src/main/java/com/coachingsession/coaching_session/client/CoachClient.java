package com.coachingsession.coaching_session.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "coach-service",fallback = CoachHystrixFallbackFactory.class)
public interface CoachClient {

    @GetMapping("/coachs/{id}")
    ResponseEntity<Coach> geById(@PathVariable("id") Long id);
}
