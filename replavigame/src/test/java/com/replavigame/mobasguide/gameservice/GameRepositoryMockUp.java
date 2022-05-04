package com.replavigame.mobasguide.gameservice;

import java.util.List;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.entity.Game;
import com.replavigame.mobasguide.repository.GameRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final public class GameRepositoryMockUp {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void whenFindAll_thenReturnListGame() {
        Game gameTest = Game.builder()
                .description("description")
                .id(1L)
                .name("name").build();
        gameRepository.save(gameTest);

        List<Game> games = gameRepository.findAll();
        Assertions.assertEquals(1, games.size());
    }

    @Test
    public void whenFindById_thenReturnGame() {
        Game gameTest = Game.builder()
                .description("description")
                .id(1L)
                .name("name").build();
        gameRepository.save(gameTest);

        Game game = gameRepository.getGameById(1L)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Game not found by id"));
        Assertions.assertEquals(1L, game.getId());
        Assertions.assertEquals("description", game.getDescription());
        Assertions.assertEquals("name", game.getName());
    }
}
