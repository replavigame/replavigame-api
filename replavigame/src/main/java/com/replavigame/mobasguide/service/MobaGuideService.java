package com.replavigame.mobasguide.service;

import java.util.List;

import com.replavigame.mobasguide.dto.MobaGuideResponse;
import com.replavigame.mobasguide.dto.MobaGuideRequest;

public interface MobaGuideService {
    List<MobaGuideResponse> getAll();

    MobaGuideResponse getMobaGuideById(Long id);

    MobaGuideResponse createMobaGuide(MobaGuideRequest request);

    MobaGuideResponse updateMobaGuide(MobaGuideRequest request, Long id);

    void deleteMobaGuideById(Long id);
}
