package com.order_game_coin.order_game_coin.service;

import java.util.List;

import com.order_game_coin.order_game_coin.dto.OrderGameCoinRequest;
import com.order_game_coin.order_game_coin.dto.OrderGameCoinResponse;

public interface OrderGameCoinService {
    List<OrderGameCoinResponse> getAll();

    OrderGameCoinResponse getById(Long id);

    OrderGameCoinResponse create(OrderGameCoinRequest request);

    OrderGameCoinResponse update(OrderGameCoinRequest request, Long id);

    void delete(Long id);
}
