package com.replavigame.mobasguide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.dto.MobaHeroRequest;
import com.replavigame.mobasguide.dto.MobaHeroResponse;
import com.replavigame.mobasguide.entity.MobaHero;
import com.replavigame.mobasguide.repository.MobaHeroRepository;
import com.replavigame.mobasguide.service.MobaHeroService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobaHeroServiceImpl implements MobaHeroService {

    @Autowired
    private MobaHeroRepository mobaHeroRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<MobaHeroResponse> getAll() {
        var entities = mobaHeroRepository.findAll();
        var response = entities.stream().map(entity -> mapper.map(entity, MobaHeroResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public MobaHeroResponse getMobaHeroById(Long id) {
        var entity = mobaHeroRepository.getMobaHeroById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba hero not found"));
        var response = mapper.map(entity, MobaHeroResponse.class);
        return response;
    }

    @Override
    public MobaHeroResponse createMobaHero(MobaHeroRequest request) {
        var entity = mapper.map(request, MobaHero.class);
        try {
            mobaHeroRepository.save(entity);
            var response = mapper.map(entity, MobaHeroResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving moba hero");
        }
    }

    @Override
    public MobaHeroResponse updateMobaHero(MobaHeroRequest request, Long id) {
        var entity = mobaHeroRepository.getMobaHeroById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba hero not found"));
        entity.setImage(request.getImage());
        entity.setName(request.getName());
        try {
            mobaHeroRepository.save(entity);
            var response = mapper.map(entity, MobaHeroResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating moba hero");
        }
    }

    @Override
    public void deleteMobaHeroById(Long id) {
        try {
            mobaHeroRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting moba hero");
        }
    }

}
