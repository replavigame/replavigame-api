package com.guide.guides.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.guide.exception.ResourceNotFoundExceptionRequest;
import com.guide.guides.dto.GuideRequest;
import com.guide.guides.dto.GuideResponse;
import com.guide.guides.entity.Guide;
import com.guide.guides.repository.CategoryRepository;
import com.guide.guides.repository.GuideRepository;
import com.guide.guides.service.GuideService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<GuideResponse> getAll() {
        var entities = guideRepository.findAll();
        var response = entities.stream().map(entity -> mapper.map(entity, GuideResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<GuideResponse> getAllByCategoryId(Long id) {
        var entities = guideRepository.findAllByCategoryId(id);
        var response = entities.stream().map(entity -> mapper.map(entity, GuideResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GuideResponse getById(Long id) {
        var entity = guideRepository.getGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Guide not found"));
        var response = mapper.map(entity, GuideResponse.class);
        return response;
    }

    @Override
    public GuideResponse create(GuideRequest request) {
        var entity = new Guide();
        var category = categoryRepository.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Category not found"));

        entity.setCategory(category);
        entity.setCoachId(request.getCoachId());
        entity.setDescount(request.getDescount());
        entity.setDescription(request.getDescription());
        entity.setGameId(request.getGameId());
        entity.setPoints(request.getPoints());
        entity.setTitle(request.getTitle());
        try {
            guideRepository.save(entity);
            var response = mapper.map(entity, GuideResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating guide");
        }
    }

    @Override
    public GuideResponse update(GuideRequest request, Long id) {
        var entity = guideRepository.getGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Guide not found"));
        var category = categoryRepository.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Category not found"));

        entity.setCategory(category);
        entity.setCoachId(request.getCoachId());
        entity.setDescount(request.getDescount());
        entity.setDescription(request.getDescription());
        entity.setGameId(request.getCoachId());
        entity.setPoints(request.getPoints());
        entity.setTitle(request.getTitle());
        try {
            guideRepository.save(entity);
            var response = mapper.map(entity, GuideResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating guide");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            guideRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting guide");
        }
    }

}
