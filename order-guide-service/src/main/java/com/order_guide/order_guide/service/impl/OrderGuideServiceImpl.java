package com.order_guide.order_guide.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.order_guide.exception.ResourceNotFoundExceptionRequest;
import com.order_guide.order_guide.dto.OrderGuideResponse;
import com.order_guide.order_guide.entity.OrderGuide;
import com.order_guide.order_guide.repository.OrderGuideRepository;
import com.order_guide.order_guide.service.OrderGuideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGuideServiceImpl implements OrderGuideService {

    @Autowired
    private OrderGuideRepository orderGuideRepository;

    private OrderGuideResponse convertToResponse(OrderGuide entity) {

        OrderGuideResponse response = new OrderGuideResponse();
        response.setUserId(entity.getCustomerId());
        response.setId(entity.getId());
        response.setSaleCreated(entity.getSaleCreated());

        return response;
    }

    private OrderGuide convertToEntity(Long userId) {
        OrderGuide orderGuide = new OrderGuide();
        orderGuide.setCustomerId(userId);
        orderGuide.setSaleCreated(new Date());

        return orderGuide;
    }

    private OrderGuide convertToEntity(Long userId, Long id) {
        OrderGuide orderGuide = new OrderGuide();
        orderGuide.setCustomerId(userId);
        orderGuide.setSaleCreated(new Date());
        orderGuide.setId(id);

        return orderGuide;
    }

    @Override
    public List<OrderGuideResponse> getAll() {
        var entities = orderGuideRepository.findAll();
        var response = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<OrderGuideResponse> getAllByCustomerId(Long id) {
        var entities = orderGuideRepository.findAllByCustomerId(id);
        var response = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
        return response;
    }

    @Override
    public OrderGuideResponse getById(Long id) {
        var entity = orderGuideRepository.getOrderGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Order guide not found"));
        var response = convertToResponse(entity);
        return response;
    }

    @Override
    public OrderGuideResponse create(Long userId) {
        var entity = convertToEntity(userId);

        try {
            orderGuideRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order guide");
        }
    }

    @Override
    public OrderGuideResponse update(Long userId, Long id) {

        var entity = orderGuideRepository.getOrderGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Order guide not found"));

        entity = convertToEntity(userId, id);

        try {
            orderGuideRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating order guide");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            orderGuideRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting order guide");
        }
    }

}
