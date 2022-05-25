package com.replavigame.order_guide.service;

import java.util.List;

import com.replavigame.order_guide.dto.DetailResponse;
import com.replavigame.order_guide.dto.OrderDetailRequest;
import com.replavigame.order_guide.dto.OrderDetailGuideResponse;

public interface OrderDetailGuideService {
    List<DetailResponse> getAll();

    OrderDetailGuideResponse getByOrderId(Long id);

    OrderDetailGuideResponse getByCoachId(Long id);

    OrderDetailGuideResponse create(OrderDetailRequest request);

    void delete(Long id);
}
