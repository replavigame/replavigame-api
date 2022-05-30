package com.detail_guide.detail_guide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.detail_guide.detail_guide.dto.DetailGuideRequest;
import com.detail_guide.detail_guide.dto.DetailGuideResponse;
import com.detail_guide.detail_guide.entity.DetailGuide;
import com.detail_guide.detail_guide.repository.DetailGuideRepository;
import com.detail_guide.detail_guide.service.DetailGuideService;
import com.detail_guide.exception.ResourceNotFoundExceptionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;

@Service
public class DetailGuideServiceImpl implements DetailGuideService {

    @Autowired
    private DetailGuideRepository detailGuideRepository;

    private DetailGuideResponse convertToResponse(DetailGuide entity) {
        DetailGuideResponse response = new DetailGuideResponse();
        response.setGuideId(entity.getGuideId());
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPosition(entity.getPosition());
        response.setVideoUrl(entity.getVideoUrl());
        return response;
    }

    private DetailGuide convertToEntity(DetailGuideRequest request) {
        DetailGuide detailGuide = new DetailGuide();
        detailGuide.setGuideId(request.getGuideId());
        detailGuide.setName(request.getName());
        detailGuide.setPosition(request.getPosition());
        detailGuide.setVideoUrl(request.getVideoUrl());
        return detailGuide;
    }

    private DetailGuide update(DetailGuide entity) {
        DetailGuide detailGuide = new DetailGuide();
        detailGuide.setGuideId(entity.getGuideId());
        detailGuide.setName(entity.getName());
        detailGuide.setPosition(entity.getPosition());
        detailGuide.setVideoUrl(entity.getVideoUrl());
        detailGuide.setId(entity.getId());
        return detailGuide;
    }

    @Override
    public List<DetailGuideResponse> getAll() {
        var entities = detailGuideRepository.findAll();
        List<DetailGuideResponse> response = entities.stream().map(entity -> convertToResponse(entity))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public DetailGuideResponse getById(Long id) {
        var entity = detailGuideRepository.getDetailGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Detail guide not found"));
        DetailGuideResponse response = convertToResponse(entity);
        return response;
    }

    @Override
    public DetailGuideResponse create(DetailGuideRequest request) {
        DetailGuide entity = convertToEntity(request);

        try {
            detailGuideRepository.save(entity);
            DetailGuideResponse response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating detail guide");
        }
    }

    @Override
    public DetailGuideResponse update(DetailGuideRequest request, Long id) {
        var entity = detailGuideRepository.getDetailGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Detail guide not found"));

        entity = update(entity);

        try {
            detailGuideRepository.save(entity);
            DetailGuideResponse response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating detail guide");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            detailGuideRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting detail guide");
        }
    }

    @Override
    public List<DetailGuideResponse> getAllByGuideId(Long id) {
        var entities = detailGuideRepository.findAllByGuideId(id);
        List<DetailGuideResponse> response = entities.stream().map(entity -> convertToResponse(entity))
                .collect(Collectors.toList());
        return response;
    }

}
