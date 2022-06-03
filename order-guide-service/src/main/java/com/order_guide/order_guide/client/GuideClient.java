package com.order_guide.order_guide.client;

import com.order_guide.order_guide.model.Guide;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "guide-service", path = "/guides")
public interface GuideClient {
    @GetMapping("/{id}")
    public ResponseEntity<Guide> getById(@PathVariable("id") Long id);
}
