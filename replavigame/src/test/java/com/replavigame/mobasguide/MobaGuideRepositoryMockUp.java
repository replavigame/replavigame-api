package com.replavigame.mobasguide;

import java.util.List;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.entity.Game;
import com.replavigame.mobasguide.entity.MobaGuide;
import com.replavigame.mobasguide.entity.MobaHero;
import com.replavigame.mobasguide.entity.MobaRole;
import com.replavigame.mobasguide.repository.GameRepository;
import com.replavigame.mobasguide.repository.MobaGuideRepository;
import com.replavigame.mobasguide.repository.MobaHeroRepository;
import com.replavigame.mobasguide.repository.MobaRoleRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MobaGuideRepositoryMockUp {

    @Autowired
    private MobaGuideRepository mobaGuideRepository;

    @Autowired
    private MobaRoleRepository mobaRoleRepository;

    @Autowired
    private MobaHeroRepository mobaHeroRepository;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void whenFindAll_thenReturnListMobaGuides() {
        Game game = Game.builder().description("descriptionGame").name("nameGame").build();
        MobaHero mobaHero = MobaHero.builder().image("imageHero").name("nameHero").build();
        MobaRole mobaRole = MobaRole.builder().description("descriptionRole").name("nameRole").build();

        gameRepository.save(game);
        mobaRoleRepository.save(mobaRole);
        mobaHeroRepository.save(mobaHero);

        MobaGuide mobaGuideTest = MobaGuide.builder()
                .description("description")
                .game(game)
                .hero(mobaHero)
                .id(1L)
                .price(153.5)
                .role(mobaRole).build();

        mobaGuideRepository.save(mobaGuideTest);

        List<MobaGuide> mobaGuides = mobaGuideRepository.findAll();
        Assertions.assertEquals(1, mobaGuides.size());
    }

    @Test
    public void whenFindById_thenReturnMobaGuide() {
        Game game = Game.builder().description("descriptionGame1").name("nameGame1").build();
        MobaHero mobaHero = MobaHero.builder().image("imageHero1").name("nameHero1").build();
        MobaRole mobaRole = MobaRole.builder().description("descriptionRole1").name("nameRole1").build();

        gameRepository.save(game);
        mobaRoleRepository.save(mobaRole);
        mobaHeroRepository.save(mobaHero);

        MobaGuide mobaGuideTest = MobaGuide.builder()
                .description("description")
                .game(game)
                .hero(mobaHero)
                .id(1L)
                .price(153.5)
                .role(mobaRole).build();

        mobaGuideRepository.save(mobaGuideTest);

        MobaGuide mobaGuide = mobaGuideRepository.getMobaGuideById(1L)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba guide not found by id"));
        Assertions.assertEquals("description", mobaGuide.getDescription());
        Assertions.assertEquals(153.5, mobaGuide.getPrice());
        Assertions.assertEquals(1L, mobaGuide.getId());
        Assertions.assertEquals(mobaRole, mobaGuide.getRole());
        Assertions.assertEquals(mobaHero, mobaGuide.getHero());
        Assertions.assertEquals(game, mobaGuide.getGame());
    }

}
