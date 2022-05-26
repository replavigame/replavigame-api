package com.replavigame.order_game_coin.service;

import java.util.List;

import com.replavigame.order_game_coin.dto.OrderDetailGameCoinRequest;
import com.replavigame.order_game_coin.dto.OrderDetailGameCoinResponse;
import com.replavigame.order_game_coin.dto.OrderDetailSimpleResponse;

public interface OrderDetailGameCoinService {

    List<OrderDetailSimpleResponse> getAll();

    OrderDetailGameCoinResponse create(OrderDetailGameCoinRequest request);

    OrderDetailGameCoinResponse getAllByOrderId(Long id);

    void deleteByOrderId(Long id);

}
