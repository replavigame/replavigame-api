package com.replavigame.mobasguide.service;

import java.util.List;

import com.replavigame.mobasguide.dto.GameRequest;
import com.replavigame.mobasguide.dto.GameResponse;

public interface GameService {
    List<GameResponse> getAll();

    GameResponse getGameById(Long id);

    GameResponse createGame(GameRequest request);

    GameResponse updateGame(GameRequest request, Long id);

    void deleteGameById(Long id);
}
