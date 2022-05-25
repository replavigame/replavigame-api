package com.replavigame.order_guide.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.order_guide.dto.OrderGuideRequest;
import com.replavigame.order_guide.dto.OrderGuideResponse;
import com.replavigame.order_guide.entity.OrderGuide;
import com.replavigame.order_guide.repository.OrderGuideRepository;
import com.replavigame.order_guide.service.OrderGuideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGuideServiceImpl implements OrderGuideService {

    @Autowired
    private OrderGuideRepository orderGuideRepository;

    private OrderGuideResponse convertToResponse(OrderGuide entity) {

        OrderGuideResponse response = new OrderGuideResponse();
        response.setCoachId(entity.getCoachId());
        response.setCustomerId(entity.getCustomerId());
        response.setId(entity.getId());
        response.setSaleCreated(entity.getSaleCreated());
        response.setTotalPrice(entity.getTotalPrice());

        return response;
    }

    private OrderGuide convertToEntity(OrderGuideRequest request) {
        OrderGuide orderGuide = new OrderGuide();
        orderGuide.setCoachId(request.getCoachId());
        orderGuide.setCustomerId(request.getCustomerId());
        orderGuide.setSaleCreated(new Date());
        orderGuide.setTotalPrice(request.getTotalPrice());

        return orderGuide;
    }

    private OrderGuide convertToEntity(OrderGuideRequest request, Long id) {
        OrderGuide orderGuide = new OrderGuide();
        orderGuide.setCoachId(request.getCoachId());
        orderGuide.setCustomerId(request.getCustomerId());
        orderGuide.setSaleCreated(new Date());
        orderGuide.setTotalPrice(request.getTotalPrice());
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
    public List<OrderGuideResponse> getAllByCoachId(Long id) {
        var entities = orderGuideRepository.findAllByCoachId(id);
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
    public OrderGuideResponse create(OrderGuideRequest request) {
        var entity = convertToEntity(request);

        try {
            orderGuideRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order guide");
        }
    }

    @Override
    public OrderGuideResponse update(OrderGuideRequest request, Long id) {

        var entity = orderGuideRepository.getOrderGuideById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Order guide not found"));

        entity = convertToEntity(request, id);

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
