package com.guide.guides.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.guide.exception.ResourceNotFoundExceptionRequest;
import com.guide.guides.dto.CategoryRequest;
import com.guide.guides.dto.CategoryResponse;
import com.guide.guides.entity.Category;
import com.guide.guides.repository.CategoryRepository;
import com.guide.guides.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CategoryResponse> getAll() {
        var entities = repository.findAll();
        var response = entities.stream().map(entity -> mapper.map(entity, CategoryResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<CategoryResponse> getAllByGameId(Long id) {
        var entities = repository.findAllByGameId(id);
        var response = entities.stream().map(entity -> mapper.map(entity, CategoryResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public CategoryResponse getById(Long id) {
        var entity = repository.getCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Category not found by id"));
        var response = mapper.map(entity, CategoryResponse.class);
        return response;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        var entity = new Category();
        entity.setGameId(request.getGameId());
        entity.setName(request.getName());
        try {
            repository.save(entity);
            var response = mapper.map(entity, CategoryResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating category");
        }
    }

    @Override
    public CategoryResponse update(CategoryRequest request, Long id) {
        var entity = repository.getCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Category not found by id"));
        entity.setGameId(request.getGameId());
        entity.setName(request.getName());
        try {
            repository.save(entity);
            var response = mapper.map(entity, CategoryResponse.class);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating category");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting category");
        }
    }

}
