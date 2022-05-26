package com.replavigame.game_coin_offer.service;

import java.util.List;

import com.replavigame.game_coin_offer.dto.GameCoinOfferRequest;
import com.replavigame.game_coin_offer.dto.GameCoinOfferResponse;

public interface GameCoinOfferService {

    List<GameCoinOfferResponse> getAll();

    GameCoinOfferResponse getById(Long id);

    GameCoinOfferResponse create(GameCoinOfferRequest request);

    GameCoinOfferResponse update(GameCoinOfferRequest request, Long id);

    void delete(Long id);

}
