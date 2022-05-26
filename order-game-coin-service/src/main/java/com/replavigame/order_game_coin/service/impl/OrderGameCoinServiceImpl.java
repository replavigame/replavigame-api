package com.replavigame.order_game_coin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.replavigame.exception.ResourceNotFoundExceptionRequest;
import com.replavigame.order_game_coin.dto.OrderGameCoinRequest;
import com.replavigame.order_game_coin.dto.OrderGameCoinResponse;
import com.replavigame.order_game_coin.entity.OrderGameCoin;
import com.replavigame.order_game_coin.repository.OrderGameCoinRepository;
import com.replavigame.order_game_coin.service.OrderGameCoinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGameCoinServiceImpl implements OrderGameCoinService {

    @Autowired
    private OrderGameCoinRepository orderGameCoinRepository;

    private OrderGameCoinResponse convertToResponse(OrderGameCoin entity) {
        OrderGameCoinResponse response = new OrderGameCoinResponse();
        response.setCustomerId(entity.getCustomerId());
        response.setId(entity.getId());
        response.setSaleOrder(entity.getSaleOrder());
        response.setTotalPrice(entity.getTotalPrice());

        return response;
    }

    private OrderGameCoin convertToEntity(OrderGameCoinRequest request) {
        OrderGameCoin orderGameCoin = new OrderGameCoin();
        orderGameCoin.setCustomerId(request.getCustomerId());
        orderGameCoin.setSaleOrder(request.getSaleOrder());
        orderGameCoin.setTotalPrice(request.getTotalPrice());

        return orderGameCoin;
    }

    private OrderGameCoin convertToEntity(OrderGameCoinRequest request, Long id) {
        OrderGameCoin orderGameCoin = new OrderGameCoin();
        orderGameCoin.setCustomerId(request.getCustomerId());
        orderGameCoin.setId(id);
        orderGameCoin.setSaleOrder(request.getSaleOrder());
        orderGameCoin.setTotalPrice(request.getTotalPrice());

        return orderGameCoin;
    }

    @Override
    public List<OrderGameCoinResponse> getAll() {
        var entities = orderGameCoinRepository.findAll();
        var response = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
        return response;
    }

    @Override
    public OrderGameCoinResponse getById(Long id) {
        var entity = orderGameCoinRepository.getOrderGameById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Order game coin not found"));

        var response = convertToResponse(entity);
        return response;
    }

    @Override
    public OrderGameCoinResponse create(OrderGameCoinRequest request) {
        var entity = convertToEntity(request);

        try {
            orderGameCoinRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order game coin");
        }
    }

    @Override
    public OrderGameCoinResponse update(OrderGameCoinRequest request, Long id) {
        var entity = orderGameCoinRepository.getOrderGameById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionRequest("Order game coin not found"));

        entity = convertToEntity(request, id);
        try {
            orderGameCoinRepository.save(entity);
            var response = convertToResponse(entity);
            return response;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while updating order game coin");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            orderGameCoinRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting order game coin");
        }
    }

}
