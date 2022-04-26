package com.replavigame.mobasguide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.dto.MobaGuideRequest;
import com.replavigame.mobasguide.dto.MobaGuideResponse;
import com.replavigame.mobasguide.entity.MobaGuide;
import com.replavigame.mobasguide.repository.GameRepository;
import com.replavigame.mobasguide.repository.MobaGuideRepository;
import com.replavigame.mobasguide.repository.MobaHeroRepository;
import com.replavigame.mobasguide.repository.MobaRoleRepository;
import com.replavigame.mobasguide.service.MobaGuideService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobaGuideServiceImpl implements MobaGuideService {

    @Autowired
    private MobaGuideRepository mobaGuideRepository;

    @Autowired
    private MobaHeroRepository mobaHeroRepository;

    @Autowired
    private MobaRoleRepository mobaRoleRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<MobaGuideResponse> getAll() {
        var entities = mobaGuideRepository.findAll();
        var response = entities.stream().map(entity -> mapper.map(entity,
                MobaGuideResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public MobaGuideResponse getMobaGuideById(Long id) {
        var entity = mobaGuideRepository.getMobaGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba guide not found by id"));
        var response = mapper.map(entity, MobaGuideResponse.class);
        return response;
    }

    @Override
    public MobaGuideResponse createMobaGuide(MobaGuideRequest request) {
        var mobaHero = mobaHeroRepository.getMobaHeroById(request.getHeroId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba hero not found by id"));
        var mobaRole = mobaRoleRepository.getMobaRoleById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba role not found by id"));
        var game = gameRepository.getGameById(request.getGameId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Game not found by id"));

        MobaGuide entity = MobaGuide.builder()
                .description(request.getDescription())
                .game(game)
                .hero(mobaHero)
                .role(mobaRole)
                .price(request.getPrice())
                .build();
        try {
            mobaGuideRepository.save(entity);
            var response = mapper.map(entity, MobaGuideResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving moba guide");
        }
    }

    @Override
    public MobaGuideResponse updateMobaGuide(MobaGuideRequest request, Long id) {
        var entity = mobaGuideRepository.getMobaGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba guide not found by id"));
        var mobaHero = mobaHeroRepository.getMobaHeroById(request.getHeroId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba hero not found by id"));
        var mobaRole = mobaRoleRepository.getMobaRoleById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba role not found by id"));
        var game = gameRepository.getGameById(request.getGameId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Game not found by id"));

        entity.setDescription(request.getDescription());
        entity.setGame(game);
        entity.setHero(mobaHero);
        entity.setRole(mobaRole);
        entity.setPrice(request.getPrice());
        try {
            mobaGuideRepository.save(entity);
            var response = mapper.map(entity, MobaGuideResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating moba guide");
        }
    }

    @Override
    public void deleteMobaGuideById(Long id) {
        try {
            mobaGuideRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting moba guide");
        }
    }

}
