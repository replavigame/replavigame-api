package com.order_guide.order_guide.client;

import com.order_guide.order_guide.model.Coach;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "coach-service", path = "/coaches")
public interface CoachClient {
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getById(@PathVariable("id") Long id);

    @PutMapping("/{id}/points")
    public ResponseEntity<Coach> updateWallet(@PathVariable("id") Long id,
            @RequestParam(name = "point", required = true) Long point);
}
