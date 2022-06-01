package com.order_guide.order_guide.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.order_guide.exception.ResourceNotFoundExceptionRequest;
import com.order_guide.order_guide.client.CoachClient;
import com.order_guide.order_guide.client.GuideClient;
import com.order_guide.order_guide.client.UserClient;
import com.order_guide.order_guide.dto.DetailResponse;
import com.order_guide.order_guide.dto.OrderDetailGuideResponse;
import com.order_guide.order_guide.dto.OrderDetailRequest;
import com.order_guide.order_guide.entity.OrderDetailGuide;
import com.order_guide.order_guide.entity.OrderDetailGuideId;
import com.order_guide.order_guide.entity.OrderGuide;
import com.order_guide.order_guide.repository.OrderDetailGuideRepository;
import com.order_guide.order_guide.repository.OrderGuideRepository;
import com.order_guide.order_guide.service.OrderDetailGuideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailGuideServiceImpl implements OrderDetailGuideService {

    @Autowired
    private OrderDetailGuideRepository orderDetailGuideRepository;

    @Autowired
    private OrderGuideRepository orderGuideRepository;

    @Autowired
    private GuideClient guideClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private CoachClient coachClient;

    private DetailResponse convertToResponse(OrderDetailGuide entity) {
        DetailResponse response = new DetailResponse();
        response.setGuideId(entity.getOrderDetailGuideId().getGuideId());
        response.setOrderId(entity.getOrderDetailGuideId().getOrderGuideId());
        response.setPrice(entity.getPrice());
        response.setCoach(entity.getCoach());
        response.setGuide(entity.getGuide());

        return response;
    }

    private OrderDetailGuideResponse convertToOrderDetailResponse(List<DetailResponse> detailResponses,
            OrderGuide orderGuide) {
        OrderDetailGuideResponse response = new OrderDetailGuideResponse();

        response.setDetailResponses(detailResponses);
        response.setCustomerId(orderGuide.getCustomerId());
        response.setSaleCreated(orderGuide.getSaleCreated());
        response.setQuantifyProducts(Long.valueOf(detailResponses.size()));
        response.setUser(orderGuide.getUser());

        return response;
    }

    @Override
    public List<DetailResponse> getAll() {
        var entities = orderDetailGuideRepository.findAll();
        var response = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());
        return response;
    }

    @Override
    public OrderDetailGuideResponse getByOrderId(Long id) {
        var entities = orderDetailGuideRepository.getAllByOrderGuideId(id);

        if (entities.size() == 0) {
            throw new ResourceNotFoundExceptionRequest("Order not found by order id");
        }

        var detail = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());

        var order = entities.get(0).getOrderGuide();

        var response = convertToOrderDetailResponse(detail, order);

        return response;
    }

    @Override
    @Transactional
    public OrderDetailGuideResponse create(OrderDetailRequest request) {

        OrderGuide orderGuide = new OrderGuide();

        orderGuide.setCustomerId(request.getUserId());
        orderGuide.setSaleCreated(new Date());

        var user = userClient.getById(request.getUserId()).getBody();
        orderGuide.setUser(user);

        try {
            orderGuideRepository.save(orderGuide);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order guide");
        }

        orderGuideRepository.save(orderGuide);
        List<OrderDetailGuide> entities = new ArrayList<OrderDetailGuide>();

        request.getDetail().forEach(detail -> {
            OrderDetailGuide orderDetailGuide = new OrderDetailGuide();
            OrderDetailGuideId orderDetailGuideId = new OrderDetailGuideId();

            var guide = guideClient.getById(detail.getGuideId()).getBody();
            var coach = coachClient.updateWallet(detail.getCoachId(), guide.getPoints()).getBody();

            // Order de los id
            orderDetailGuideId.setGuideId(detail.getGuideId());
            orderDetailGuideId.setOrderGuideId(orderGuide.getId());

            // Empezamos a crear nuestras ordenes que son elaborados en un carrito de
            // compras
            orderDetailGuide.setOrderDetailGuideId(orderDetailGuideId);
            orderDetailGuide.setPrice(guide.getPoints());
            orderDetailGuide.setOrderGuide(orderGuide);
            orderDetailGuide.setGuide(guide);
            orderDetailGuide.setCoach(coach);

            orderDetailGuideRepository.save(orderDetailGuide);

            // Guardamos todas las compras para pasarlo luego en un response
            entities.add(orderDetailGuide);
        });

        var detail = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());

        var response = convertToOrderDetailResponse(detail, orderGuide);

        return response;
    }

    @Override
    public void delete(Long id) {
        try {
            orderDetailGuideRepository.deleteAllByOrderGuideId(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting order guide");
        }
        try {
            orderGuideRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while deleting order guide");
        }
    }

}
