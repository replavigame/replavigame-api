package com.order_game_coin.order_game_coin.client;

import com.order_game_coin.order_game_coin.model.GameCoin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-coin-service", path = "/game-coin-offers", fallback = GameCoinHystrixFallbackFactory.class)
public interface GameCoinClient {
    @GetMapping("/{id}")
    public ResponseEntity<GameCoin> getById(@PathVariable("id") Long id);
}
