package com.coachreport.coach_report.client;

import com.coachreport.coach_report.models.Coach;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "coach-service",path = "/coachs",fallback = CoachHystrixFallbackFactory.class)
public interface CoachClient {

    @GetMapping("/{id}")
    public ResponseEntity<Coach> geById(@PathVariable("id") Long id);
}
