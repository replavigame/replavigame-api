package com.replavigame.coachingsession.service.impl;

import com.replavigame.coachingsession.dto.PlatformRequest;
import com.replavigame.coachingsession.dto.PlatformResponse;
import com.replavigame.coachingsession.entity.Platform;
import com.replavigame.coachingsession.repository.PlatformRepository;
import com.replavigame.coachingsession.repository.SessionRepository;
import com.replavigame.coachingsession.service.PlatformService;
import com.replavigame.coachingsession.exception.ResourceNotFoundExceptionRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<PlatformResponse> getAll() {
        var entities = platformRepository.findAll();
        return entities.stream().map(entity -> mapper.map(entity, PlatformResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlatformResponse> getAllBySessionId(Long id) {
        var entities = platformRepository.findAllBySessionId(id);
        return entities.stream().map(entity -> mapper.map(entity, PlatformResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlatformResponse getById(Long id) {
        var entity = platformRepository.getPlatformById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Platform not found"));
        return mapper.map(entity, PlatformResponse.class);
    }

    @Override
    public PlatformResponse create(PlatformRequest platformRequest) {
        var entity = new Platform();
        var session = sessionRepository.getSessionById(platformRequest.getSession_id())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Session not found"));

        entity.setSession(session);
        entity.setName(platformRequest.getName());
        entity.setUrl(platformRequest.getUrl());

        try {
            platformRepository.save(entity);
            return mapper.map(entity, PlatformResponse.class);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while creating platform");
        }
    }

    @Override
    public PlatformResponse update(PlatformRequest platformRequest, Long id) {
        var entity = platformRepository.getPlatformById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Platform not found"));
        var session = sessionRepository.getSessionById(platformRequest.getSession_id())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Session not found"));

        entity.setSession(session);
        entity.setName(platformRequest.getName());
        entity.setUrl(platformRequest.getUrl());

        try {
            platformRepository.save(entity);
            return mapper.map(entity, PlatformResponse.class);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while creating platform");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            platformRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while updating guide");
        }
    }
}
