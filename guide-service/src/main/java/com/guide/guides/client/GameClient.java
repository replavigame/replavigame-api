package com.guide.guides.client;

import com.guide.guides.model.Game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service", path = "/games")
public interface GameClient {
    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") Long id);
}
