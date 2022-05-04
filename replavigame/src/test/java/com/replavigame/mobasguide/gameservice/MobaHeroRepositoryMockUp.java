package com.replavigame.mobasguide.gameservice;

import java.util.List;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.entity.MobaHero;
import com.replavigame.mobasguide.repository.MobaHeroRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final public class MobaHeroRepositoryMockUp {

    @Autowired
    private MobaHeroRepository mobaHeroRepository;

    @Test
    public void whenFindAll_thenReturnListMobaHero() {
        MobaHero mobaHeroTest = MobaHero.builder()
                .id(1L)
                .image("image")
                .name("name").build();
        mobaHeroRepository.save(mobaHeroTest);

        List<MobaHero> games = mobaHeroRepository.findAll();
        Assertions.assertEquals(1, games.size());
    }

    @Test
    public void whenFindById_thenReturnMobaHero() {
        MobaHero mobaHeroTest = MobaHero.builder()
                .id(1L)
                .image("image")
                .name("name").build();
        mobaHeroRepository.save(mobaHeroTest);

        MobaHero mobaHero = mobaHeroRepository.getMobaHeroById(1L)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Game not found by id"));
        Assertions.assertEquals(1L, mobaHero.getId());
        Assertions.assertEquals("image", mobaHero.getImage());
        Assertions.assertEquals("name", mobaHero.getName());
    }
}
