package com.order_game_coin.order_game_coin.repository;

import java.util.Optional;

import com.order_game_coin.order_game_coin.entity.OrderGameCoin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGameCoinRepository extends JpaRepository<OrderGameCoin, Long> {
    Optional<OrderGameCoin> getOrderGameById(Long id);
}
