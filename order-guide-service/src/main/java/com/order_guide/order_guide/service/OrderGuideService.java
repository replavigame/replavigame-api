package com.order_guide.order_guide.service;

import java.util.List;

import com.order_guide.order_guide.dto.OrderGuideResponse;

public interface OrderGuideService {

    List<OrderGuideResponse> getAll();

    List<OrderGuideResponse> getAllByCustomerId(Long id);

    OrderGuideResponse getById(Long id);

    OrderGuideResponse create(Long userId);

    OrderGuideResponse update(Long userId, Long id);

    void delete(Long id);
}
