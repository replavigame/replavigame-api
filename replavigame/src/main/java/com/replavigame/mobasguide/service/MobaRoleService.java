package com.replavigame.mobasguide.service;

import java.util.List;

import com.replavigame.mobasguide.dto.MobaRoleRequest;
import com.replavigame.mobasguide.dto.MobaRoleResponse;

public interface MobaRoleService {
    List<MobaRoleResponse> getAll();

    MobaRoleResponse getByMobaRoleById(Long id);

    MobaRoleResponse createMobaRole(MobaRoleRequest request);

    MobaRoleResponse updateMobaRole(MobaRoleRequest request, Long id);

    void deleteMobaRoleById(Long id);
}
