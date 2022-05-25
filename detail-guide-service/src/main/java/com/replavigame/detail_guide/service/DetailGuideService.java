package com.replavigame.detail_guide.service;

import java.util.List;

import com.replavigame.detail_guide.dto.DetailGuideRequest;
import com.replavigame.detail_guide.dto.DetailGuideResponse;

public interface DetailGuideService {

    List<DetailGuideResponse> getAll();

    List<DetailGuideResponse> getAllByGuideId(Long id);

    DetailGuideResponse getById(Long id);

    DetailGuideResponse create(DetailGuideRequest request);

    DetailGuideResponse update(DetailGuideRequest request, Long id);

    void delete(Long id);
}
