package com.checkout.checkout.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.checkout.checkout.client.UserClient;
import com.checkout.checkout.dto.DetailCardRequest;
import com.checkout.checkout.dto.DetailCardResponse;
import com.checkout.checkout.dto.UserDetailCardResponse;
import com.checkout.checkout.entity.DetailCard;
import com.checkout.checkout.model.User;
import com.checkout.checkout.repository.DetailCardRepository;
import com.checkout.checkout.service.DetailCardService;
import com.checkout.exception.ResourceNotFoundExceptionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailCardServiceImpl implements DetailCardService {

    @Autowired
    private DetailCardRepository detailCardRepository;

    @Autowired
    private UserClient userClient;

    private DetailCardResponse convertToResponse(DetailCard entity) {
        DetailCardResponse response = new DetailCardResponse();
        response.setCardholder(entity.getCardholder());
        response.setCode(entity.getCode());
        response.setDay(entity.getDay());
        response.setId(entity.getId());
        response.setMonth(entity.getMonth());
        response.setObfuscatedCard(entity.getObfuscateCard());
        response.setUserId(entity.getUserId());
        return response;
    }

    private DetailCard convertToEntity(DetailCardRequest request) {
        DetailCard entity = new DetailCard();
        entity.setCardholder(request.getCardholder());
        entity.setCode(request.getCode());
        entity.setDay(request.getDay());
        entity.setMonth(request.getMonth());
        entity.setObfuscateCard(request.getObfuscatedCard());
        entity.setUserId(request.getUserId());
        return entity;
    }

    private DetailCard convertToEntity(DetailCardRequest request, Long id) {
        DetailCard entity = new DetailCard();
        entity.setCardholder(request.getCardholder());
        entity.setCode(request.getCode());
        entity.setDay(request.getDay());
        entity.setMonth(request.getMonth());
        entity.setObfuscateCard(request.getObfuscatedCard());
        entity.setUserId(request.getUserId());
        entity.setId(id);
        return entity;
    }

    @Override
    public List<DetailCardResponse> getAll() {
        var entities = detailCardRepository.findAll();
        var response = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
        return response;
    }

    @Override
    public UserDetailCardResponse getAllByUserId(Long id) {
        var entities = detailCardRepository.findAllByUserId(id);
        var detailCardResponse = entities.stream().map(entity -> convertToResponse(entity))
                .collect(Collectors.toList());

        User user = userClient.geById(id).getBody();

        UserDetailCardResponse response = new UserDetailCardResponse();
        response.setDetailCardResponse(detailCardResponse);
        response.setUser(user);
        return response;
    }

    @Override
    public DetailCardResponse getById(Long id) {
        var entity = detailCardRepository.getDetailCardById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Card not found"));
        var response = convertToResponse(entity);
        return response;
    }

    @Override
    public DetailCardResponse create(DetailCardRequest request) {
        var entity = convertToEntity(request);
        try {
            detailCardRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating card");
        }
    }

    @Override
    public DetailCardResponse update(DetailCardRequest request, Long id) {
        var entity = detailCardRepository.getDetailCardById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Card not found"));
        entity = convertToEntity(request, id);

        try {
            detailCardRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating card");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            detailCardRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting card");
        }
    }

}
