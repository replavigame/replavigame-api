package com.replavigame.order_game_coin.repository;

import java.util.Optional;

import com.replavigame.order_game_coin.entity.OrderGameCoin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGameCoinRepository extends JpaRepository<OrderGameCoin, Long> {
    Optional<OrderGameCoin> getOrderGameById(Long id);
}
