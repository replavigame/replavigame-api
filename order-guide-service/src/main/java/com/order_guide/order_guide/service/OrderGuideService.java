package com.order_guide.order_guide.service;

import java.util.List;

import com.order_guide.order_guide.dto.OrderGuideRequest;
import com.order_guide.order_guide.dto.OrderGuideResponse;

public interface OrderGuideService {

    List<OrderGuideResponse> getAll();

    List<OrderGuideResponse> getAllByCustomerId(Long id);

    List<OrderGuideResponse> getAllByCoachId(Long id);

    OrderGuideResponse getById(Long id);

    OrderGuideResponse create(OrderGuideRequest request);

    OrderGuideResponse update(OrderGuideRequest request, Long id);

    void delete(Long id);
}
