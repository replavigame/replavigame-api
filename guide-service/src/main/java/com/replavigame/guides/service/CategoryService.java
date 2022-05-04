package com.replavigame.guides.service;

import java.util.List;

import com.replavigame.guides.dto.CategoryRequest;
import com.replavigame.guides.dto.CategoryResponse;

public interface CategoryService {
    List<CategoryResponse> getAll();

    List<CategoryResponse> getAllByGameId(Long id);

    CategoryResponse getById(Long id);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(CategoryRequest request, Long id);

    void delete(Long id);
}
