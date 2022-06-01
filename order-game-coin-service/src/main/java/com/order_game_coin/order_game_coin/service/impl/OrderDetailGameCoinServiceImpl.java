package com.order_game_coin.order_game_coin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.order_game_coin.exception.ResourceNotFoundExceptionRequest;
import com.order_game_coin.order_game_coin.dto.DetailGameCoinResponse;
import com.order_game_coin.order_game_coin.dto.OrderDetailGameCoinRequest;
import com.order_game_coin.order_game_coin.dto.OrderDetailGameCoinResponse;
import com.order_game_coin.order_game_coin.dto.OrderDetailSimpleResponse;
import com.order_game_coin.order_game_coin.dto.OrderGameCoinResponse;
import com.order_game_coin.order_game_coin.entity.OrderDetailGameCoin;
import com.order_game_coin.order_game_coin.entity.OrderDetailGameCoinId;
import com.order_game_coin.order_game_coin.entity.OrderGameCoin;
import com.order_game_coin.order_game_coin.repository.OrderDetailGameCoinRepository;
import com.order_game_coin.order_game_coin.repository.OrderGameCoinRepository;
import com.order_game_coin.order_game_coin.service.OrderDetailGameCoinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailGameCoinServiceImpl implements OrderDetailGameCoinService {

    @Autowired
    private OrderDetailGameCoinRepository orderDetailGameCoinRepository;

    @Autowired
    private OrderGameCoinRepository orderGameCoinRepository;

    private OrderDetailSimpleResponse convertResponseSimple(OrderDetailGameCoin entity) {
        OrderDetailSimpleResponse response = new OrderDetailSimpleResponse();
        response.setCustomerId(entity.getOrderGameCoin().getCustomerId());
        response.setGameCoinId(entity.getOrderDetailGameCoinId().getGameCoinId());
        response.setOrderId(entity.getOrderGameCoin().getId());
        response.setQuantify(entity.getQuantify());
        response.setSubtotal(entity.getSubtotal());

        return response;
    }

    private DetailGameCoinResponse convertToResponseDetail(OrderDetailGameCoin entity) {
        DetailGameCoinResponse response = new DetailGameCoinResponse();
        response.setGameCoinId(entity.getOrderDetailGameCoinId().getGameCoinId());
        response.setQuantify(entity.getQuantify());
        response.setSubtotal(entity.getSubtotal());

        return response;
    }

    private OrderGameCoinResponse convertToResponseOrder(OrderGameCoin entity) {
        OrderGameCoinResponse response = new OrderGameCoinResponse();
        response.setCustomerId(entity.getCustomerId());
        response.setId(entity.getId());
        response.setSaleOrder(entity.getSaleOrder());
        response.setTotalPrice(entity.getTotalPrice());

        return response;
    }

    @Override
    public List<OrderDetailSimpleResponse> getAll() {
        var entities = orderDetailGameCoinRepository.findAll();
        var response = entities.stream().map(entity -> convertResponseSimple(entity)).collect(Collectors.toList());
        return response;
    }

    @Override
    public OrderDetailGameCoinResponse getAllByOrderId(Long id) {
        var entities = orderDetailGameCoinRepository.getAllByOrderId(id);

        if (entities.size() == 0) {
            throw new ResourceNotFoundExceptionRequest("Order detail is empty");
        }

        OrderDetailGameCoinResponse response = new OrderDetailGameCoinResponse();
        List<DetailGameCoinResponse> detailGameCoin = new ArrayList<DetailGameCoinResponse>();
        entities.forEach(entity -> {
            detailGameCoin.add(convertToResponseDetail(entity));
        });

        var order = entities.get(0).getOrderGameCoin();

        response.setLCoinResponses(detailGameCoin);
        response.setOrderGameCoinResponse(convertToResponseOrder(order));
        response.setCustomerId(order.getCustomerId());

        return response;
    }

    @Override
    public OrderDetailGameCoinResponse create(OrderDetailGameCoinRequest request) {

        OrderGameCoin orderGameCoin = new OrderGameCoin();
        orderGameCoin.setCustomerId(request.getCustomerId());
        orderGameCoin.setSaleOrder(request.getSaleOrder());
        orderGameCoin.setTotalPrice(request.getTotal());

        try {
            orderGameCoinRepository.save(orderGameCoin);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order game coin");
        }

        OrderDetailGameCoinResponse response = new OrderDetailGameCoinResponse();
        List<DetailGameCoinResponse> detailGameCoin = new ArrayList<DetailGameCoinResponse>();
        OrderDetailGameCoinId orderDetailGameCoinId = new OrderDetailGameCoinId();
        OrderDetailGameCoin entity = new OrderDetailGameCoin();
        request.getLCoinRequests().forEach(coinRequest -> {

            orderDetailGameCoinId.setGameCoinId(coinRequest.getGameCoinId());
            orderDetailGameCoinId.setOrderId(orderGameCoin.getId());

            entity.setOrderDetailGameCoinId(orderDetailGameCoinId);
            entity.setOrderGameCoin(orderGameCoin);
            entity.setQuantify(coinRequest.getQuantify());

            var subTotal = coinRequest.getQuantify() * coinRequest.getPriceGameCoin();

            entity.setSubtotal(subTotal);
            detailGameCoin.add(convertToResponseDetail(entity));

            try {
                orderDetailGameCoinRepository.save(entity);
            } catch (Exception e) {
                throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order detail game coin");
            }

        });

        response.setLCoinResponses(detailGameCoin);
        response.setCustomerId(null);
        response.setOrderGameCoinResponse(convertToResponseOrder(orderGameCoin));

        return response;
    }

    @Override
    public void deleteByOrderId(Long id) {
        try {
            orderDetailGameCoinRepository.deleteAllByOrderId(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting order detail game coin");
        }

        try {
            orderGameCoinRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting order game coin");
        }
    }

}
