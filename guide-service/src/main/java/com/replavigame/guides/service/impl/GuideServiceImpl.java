package com.replavigame.guides.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.guides.dto.CategoryResponse;
import com.replavigame.guides.dto.GuideRequest;
import com.replavigame.guides.dto.GuideResponse;
import com.replavigame.guides.entity.Category;
import com.replavigame.guides.entity.Guide;
import com.replavigame.guides.repository.CategoryRepository;
import com.replavigame.guides.repository.GuideRepository;
import com.replavigame.guides.service.GuideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private GuideResponse convertToResponse(Guide guide) {
        GuideResponse response = new GuideResponse();
        CategoryResponse category = new CategoryResponse();
        category.setGameId(guide.getCategory().getGameId());
        category.setId(guide.getCategory().getId());
        category.setName(guide.getCategory().getName());

        response.setCategory(category);
        response.setCoachId(guide.getCoachId());
        response.setDescount(guide.getDescount());
        response.setDescription(guide.getDescription());
        response.setGameId(guide.getGameId());
        response.setId(guide.getId());
        response.setPoints(guide.getPoints());
        response.setTitle(guide.getTitle());

        return response;
    }

    private Guide convertToEntity(GuideRequest request, Category category) {
        Guide guide = new Guide();

        guide.setCategory(category);
        guide.setCoachId(request.getCoachId());
        guide.setDescount(request.getDescount());
        guide.setDescription(request.getDescription());
        guide.setGameId(request.getGameId());
        guide.setPoints(request.getPoints());
        guide.setTitle(request.getTitle());

        return guide;
    }

    private Guide convertToEntity(GuideRequest request, Category category, Long id) {
        Guide guide = new Guide();

        guide.setCategory(category);
        guide.setCoachId(request.getCoachId());
        guide.setDescount(request.getDescount());
        guide.setDescription(request.getDescription());
        guide.setGameId(request.getGameId());
        guide.setId(id);
        guide.setPoints(request.getPoints());
        guide.setTitle(request.getTitle());

        return guide;
    }

    @Override
    public List<GuideResponse> getAll() {
        var entities = guideRepository.findAll();
        var response = entities.stream().map(entity -> convertToResponse(entity))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<GuideResponse> getAllByCategoryId(Long id) {
        var entities = guideRepository.findAllByCategoryId(id);
        var response = entities.stream().map(entity -> convertToResponse(entity))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GuideResponse getById(Long id) {
        var entity = guideRepository.getGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Guide not found"));
        var response = convertToResponse(entity);
        return response;
    }

    @Override
    public GuideResponse create(GuideRequest request) {
        var category = categoryRepository.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Category not found"));

        var entity = convertToEntity(request, category);

        try {
            guideRepository.save(entity);
            var response = convertToResponse(entity);
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

        entity = convertToEntity(request, category, id);

        try {
            guideRepository.save(entity);
            var response = convertToResponse(entity);
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
