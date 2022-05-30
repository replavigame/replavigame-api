package com.replavigame.coachingsession.service.impl;

import com.replavigame.coachingsession.dto.SessionRequest;
import com.replavigame.coachingsession.dto.SessionResponse;
import com.replavigame.coachingsession.entity.Session;
import com.replavigame.coachingsession.repository.SessionRepository;
import com.replavigame.coachingsession.service.SessionService;
import com.replavigame.coachingsession.exception.ResourceNotFoundExceptionRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<SessionResponse> getAll() {
        var entities = repository.findAll();
        return entities.stream().map(entity -> mapper.map(entity, SessionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionResponse> getSessionsByRange(Date start, Date end) {
        var entities = repository.findAll();
        var filtering = entities.stream().filter(ent -> ent.getStartDate().compareTo(start) > 0 && ent.getEndDate().compareTo(end) < 0  );
        return filtering.map(entity -> mapper.map(entity,SessionResponse.class)).collect(Collectors.toList());
    }


    @Override
    public SessionResponse getById(Long id) {
        var entity = repository.getSessionById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Session not found"));
        return mapper.map(entity, SessionResponse.class);
    }

    @Override
    public SessionResponse create(SessionRequest sessionRequest) {
        var entity = new Session();

        entity.setName(sessionRequest.getName());
        entity.setStartDate(sessionRequest.getStartDate());
        entity.setEndDate(sessionRequest.getEndDate());
        entity.setAvailable(sessionRequest.getAvailable());

        try {
            repository.save(entity);
            return mapper.map(entity, SessionResponse.class);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while creating session");
        }
    }

    @Override
    public SessionResponse update(SessionRequest sessionRequest, Long id) {

        var entity = repository.getSessionById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Session not found"));

        entity.setName(sessionRequest.getName());
        entity.setStartDate(sessionRequest.getStartDate());
        entity.setEndDate(sessionRequest.getEndDate());
        entity.setAvailable(sessionRequest.getAvailable());
        try {
            repository.save(entity);
            return mapper.map(entity, SessionResponse.class);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while updating Session");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error occurred while deleting Session");
        }

    }
}
