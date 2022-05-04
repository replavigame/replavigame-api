package com.replavigame.guides.service;

import java.util.List;

import com.replavigame.guides.dto.GuideRequest;
import com.replavigame.guides.dto.GuideResponse;

public interface GuideService {
    List<GuideResponse> getAll();

    List<GuideResponse> getAllByCategoryId(Long id);

    GuideResponse getById(Long id);

    GuideResponse create(GuideRequest request);

    GuideResponse update(GuideRequest request, Long id);

    void delete(Long id);
}
