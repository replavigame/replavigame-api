package com.replavigame.mobasguide.service;

import java.util.List;

import com.replavigame.mobasguide.dto.MobaHeroRequest;
import com.replavigame.mobasguide.dto.MobaHeroResponse;

public interface MobaHeroService {
    List<MobaHeroResponse> getAll();

    MobaHeroResponse getMobaHeroById(Long id);

    MobaHeroResponse createMobaHero(MobaHeroRequest request);

    MobaHeroResponse updateMobaHero(MobaHeroRequest request, Long id);

    void deleteMobaHeroById(Long id);
}
