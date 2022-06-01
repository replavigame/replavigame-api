package com.order_guide.order_guide.service;

import java.util.List;

import com.order_guide.order_guide.dto.DetailResponse;
import com.order_guide.order_guide.dto.OrderDetailGuideResponse;
import com.order_guide.order_guide.dto.OrderDetailRequest;

public interface OrderDetailGuideService {
    List<DetailResponse> getAll();

    OrderDetailGuideResponse getByOrderId(Long id);

    OrderDetailGuideResponse getByCoachId(Long id);

    OrderDetailGuideResponse create(OrderDetailRequest request);

    void delete(Long id);
}
