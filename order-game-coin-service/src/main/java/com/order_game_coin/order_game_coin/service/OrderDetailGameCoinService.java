package com.order_game_coin.order_game_coin.service;

import java.util.List;

import com.order_game_coin.order_game_coin.dto.OrderDetailGameCoinRequest;
import com.order_game_coin.order_game_coin.dto.OrderDetailGameCoinResponse;
import com.order_game_coin.order_game_coin.dto.OrderDetailSimpleResponse;

public interface OrderDetailGameCoinService {

    List<OrderDetailSimpleResponse> getAll();

    OrderDetailGameCoinResponse create(OrderDetailGameCoinRequest request);

    OrderDetailGameCoinResponse getAllByOrderId(Long id);

    void deleteByOrderId(Long id);

}
