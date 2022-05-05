package com.replavigame.security.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.security.dto.AuthenticateRequest;
import com.replavigame.security.dto.CoachRequest;
import com.replavigame.security.dto.CoachResponse;
import com.replavigame.security.entity.Coach;
import com.replavigame.security.repository.CoachRepository;
import com.replavigame.security.service.CoachService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CoachResponse> getAll() {
        var entities = userRepository.findAll();
        var response = entities.stream().map(entity -> mapper.map(entity, CoachResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public CoachResponse getById(Long id) {
        var entity = userRepository.getCoachById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("User not found by id"));
        var response = mapper.map(entity, CoachResponse.class);
        return response;
    }

    @Override
    public CoachResponse getByUsernameAndPassword(AuthenticateRequest request) {
        var entity = userRepository.getCoachByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("User not found by id"));
        var valid = encoder.matches(request.getPassword(), entity.getPassword());
        if (valid) {
            var response = mapper.map(entity, CoachResponse.class);
            return response;
        }
        throw new ResourceNotFoundExceptionRequest("Credentials invalids");
    }

    @Override
    public CoachResponse create(CoachRequest request) {
        var username = userRepository.getCoachByUsername(request.getUsername())
                .orElse(null);
        if (username != null) {
            throw new ResourceNotFoundExceptionRequest("Username not valid");
        }
        var email = userRepository.getCoachByEmail(request.getEmail())
                .orElse(null);
        if (email != null) {
            throw new ResourceNotFoundExceptionRequest("Email not valid");
        }
        var number = userRepository.getCoachByNumber(request.getNumber())
                .orElse(null);
        if (number != null) {
            throw new ResourceNotFoundExceptionRequest("Number not valid");
        }
        var nameCoach = userRepository.getCoachByNameCoach(request.getNameCoach())
                .orElse(null);
        if (nameCoach != null) {
            throw new ResourceNotFoundExceptionRequest("Number not valid");
        }

        var entity = new Coach();
        entity.setBirthday(request.getBirthday());
        entity.setCreatedDate(request.getCreatedDate());
        entity.setDescription(request.getDescription());
        entity.setEmail(request.getEmail());
        entity.setGameId(request.getGameId());
        entity.setLastName(request.getLastName());
        entity.setName(request.getName());
        entity.setNameCoach(request.getNameCoach());
        entity.setNumber(request.getNumber());
        entity.setPoints(0L);
        var passwordEncrypt = encoder.encode(request.getPassword());
        entity.setPassword(passwordEncrypt);

        try {
            userRepository.save(entity);
            var response = mapper.map(entity, CoachResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating user");
        }
    }

    @Override
    public CoachResponse update(CoachRequest request, Long id) {
        var entity = userRepository.getCoachById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("User not found by id"));
        var username = userRepository.getCoachByUsername(request.getUsername())
                .orElse(null);
        if (username != null) {
            throw new ResourceNotFoundExceptionRequest("Username not valid");
        }
        var email = userRepository.getCoachByEmail(request.getEmail())
                .orElse(null);
        if (email != null) {
            throw new ResourceNotFoundExceptionRequest("Email not valid");
        }
        var number = userRepository.getCoachByNumber(request.getNumber())
                .orElse(null);
        if (number != null) {
            throw new ResourceNotFoundExceptionRequest("Number not valid");
        }
        var nameCoach = userRepository.getCoachByNameCoach(request.getNameCoach())
                .orElse(null);
        if (nameCoach != null) {
            throw new ResourceNotFoundExceptionRequest("Number not valid");
        }

        entity.setBirthday(request.getBirthday());
        entity.setCreatedDate(request.getCreatedDate());
        entity.setDescription(request.getDescription());
        entity.setEmail(request.getEmail());
        entity.setGameId(request.getGameId());
        entity.setLastName(request.getLastName());
        entity.setName(request.getName());
        entity.setNameCoach(request.getNameCoach());
        entity.setNumber(request.getNumber());
        entity.setPoints(0L);
        var passwordEncrypt = encoder.encode(request.getPassword());
        entity.setPassword(passwordEncrypt);

        try {
            userRepository.save(entity);
            var response = mapper.map(entity, CoachResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating user");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting user");
        }
    }

}
