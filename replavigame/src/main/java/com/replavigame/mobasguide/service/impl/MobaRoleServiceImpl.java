package com.replavigame.mobasguide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.mobasguide.dto.MobaRoleRequest;
import com.replavigame.mobasguide.dto.MobaRoleResponse;
import com.replavigame.mobasguide.entity.MobaRole;
import com.replavigame.mobasguide.repository.MobaRoleRepository;
import com.replavigame.mobasguide.service.MobaRoleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobaRoleServiceImpl implements MobaRoleService {

    @Autowired
    private MobaRoleRepository mobaRoleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<MobaRoleResponse> getAll() {
        var entities = mobaRoleRepository.findAll();
        var response = entities.stream().map(entity -> mapper.map(entity, MobaRoleResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public MobaRoleResponse getByMobaRoleById(Long id) {
        var entity = mobaRoleRepository.getMobaRoleById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba Role not found by id"));
        var response = mapper.map(entity, MobaRoleResponse.class);
        return response;
    }

    @Override
    public MobaRoleResponse createMobaRole(MobaRoleRequest request) {
        var entity = mapper.map(request, MobaRole.class);
        try {
            mobaRoleRepository.save(entity);
            var response = mapper.map(entity, MobaRoleResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving moba role");
        }
    }

    @Override
    public MobaRoleResponse updateMobaRole(MobaRoleRequest request, Long id) {
        var entity = mobaRoleRepository.getMobaRoleById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Moba Role not found by id"));
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        try {
            mobaRoleRepository.save(entity);
            var response = mapper.map(entity, MobaRoleResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating moba role");
        }
    }

    @Override
    public void deleteMobaRoleById(Long id) {
        try {
            mobaRoleRepository.deleteById(id);
            ;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting moba role");
        }
    }

}
