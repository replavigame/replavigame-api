package com.coachingsession.coaching_session.service.impl;

import com.coachingsession.coaching_session.dto.SessionRequest;
import com.coachingsession.coaching_session.dto.SessionResponse;
import com.coachingsession.coaching_session.entity.Session;
import com.coachingsession.coaching_session.repository.SessionRepository;
import com.coachingsession.coaching_session.service.SessionService;
import com.coachingsession.exception.ResourceNotFoundExceptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository repository;

    private SessionResponse convertToResponse(Session entity) {
        SessionResponse response = new SessionResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEndDate(entity.getEndDate());
        response.setStartDate(entity.getStartDate());
        response.setAvailable(entity.getAvailable());
        return response;
    }

    private Session convertToEntity(SessionRequest request) {
        Session session = new Session();
        session.setEndDate(request.getEndDate());
        session.setStartDate(request.getStartDate());
        session.setAvailable(request.getAvailable());
        session.setName(request.getName());
        return session;
    }


    @Override
    public List<SessionResponse> getAll() {
        var entities = repository.findAll();
        return entities.stream().map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionResponse> getSessionsByRange(Date start, Date end) {
        var entities = repository.findAll();
        var filtering = entities.stream().filter(ent -> ent.getStartDate().compareTo(start) > 0 && ent.getEndDate().compareTo(end) < 0  );
        return filtering.map(this::convertToResponse).collect(Collectors.toList());
    }


    @Override
    public SessionResponse getById(Long id) {
        var entity = repository.getSessionById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Session not found"));
        return convertToResponse(entity);
    }

    @Override
    public SessionResponse create(SessionRequest sessionRequest) {
        var entity = new Session();

        entity = convertToEntity(sessionRequest);

        try {
            repository.save(entity);
            return convertToResponse(entity);
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
            return convertToResponse(entity);
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
