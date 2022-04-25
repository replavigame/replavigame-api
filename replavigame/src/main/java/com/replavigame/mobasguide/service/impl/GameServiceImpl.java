package com.replavigame.mobasguide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.dto.GameRequest;
import com.replavigame.mobasguide.dto.GameResponse;
import com.replavigame.mobasguide.entity.Game;
import com.replavigame.mobasguide.repository.GameRepository;
import com.replavigame.mobasguide.service.GameService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<GameResponse> getAll() {
        var entities = gameRepository.findAll();
        var response = entities.stream().map(entity -> mapper.map(
                entity, GameResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public GameResponse getGameById(Long id) {
        var entity = gameRepository.getGameById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Game not found by id"));
        var response = mapper.map(entity, GameResponse.class);
        return response;
    }

    @Override
    public GameResponse createGame(GameRequest request) {
        var entity = mapper.map(request, Game.class);
        try {
            gameRepository.save(entity);
            var response = mapper.map(entity, GameResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving game");
        }
    }

    @Override
    public GameResponse updateGame(GameRequest request, Long id) {
        var entity = gameRepository.getGameById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Game not found by id"));

        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        try {
            gameRepository.save(entity);
            var response = mapper.map(entity, GameResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating game");
        }
    }

    @Override
    public void deleteGameById(Long id) {
        try {
            gameRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting game");
        }
    }

}
