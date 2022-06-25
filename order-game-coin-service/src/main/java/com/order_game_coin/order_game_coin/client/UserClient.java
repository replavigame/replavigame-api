package com.order_game_coin.order_game_coin.client;

import com.order_game_coin.order_game_coin.model.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", path = "/users", fallback = UserHystrixFallbackFactory.class)
public interface UserClient {
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id);

    @PutMapping("/{id}/points")
    public ResponseEntity<User> updateWallet(@RequestParam(value = "points") Long points, @PathVariable("id") Long id);
}