package com.order_guide.order_guide.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.order_guide.exception.ResourceNotFoundExceptionRequest;
import com.order_guide.order_guide.client.CoachClient;
import com.order_guide.order_guide.client.GuideClient;
import com.order_guide.order_guide.client.UserClient;
import com.order_guide.order_guide.dto.CoachGuideDetail;
import com.order_guide.order_guide.dto.CoachOrderDetailResponse;
import com.order_guide.order_guide.dto.DetailResponse;
import com.order_guide.order_guide.dto.OrderDetailGuideResponse;
import com.order_guide.order_guide.dto.OrderDetailRequest;
import com.order_guide.order_guide.entity.OrderDetailGuide;
import com.order_guide.order_guide.entity.OrderDetailGuideId;
import com.order_guide.order_guide.entity.OrderGuide;
import com.order_guide.order_guide.model.Coach;
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
        response.setCoachId(entity.getCoachId());

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

        var total = 0L;

        var detailResponse = entities.stream().map(entity -> {
            DetailResponse detail = new DetailResponse();

            var coachId = entity.getCoachId();
            var guideId = entity.getOrderDetailGuideId().getGuideId();
            var orderId = entity.getOrderDetailGuideId().getOrderGuideId();

            var coach = coachClient.geById(coachId).getBody();
            var guide = guideClient.getById(guideId).getBody();

            var price = guide.getPoints();

            detail.setCoach(coach);
            detail.setGuide(guide);
            detail.setGuideId(guideId);
            detail.setOrderId(orderId);
            detail.setPrice(price);
            detail.setCoachId(coachId);

            // total = total + price;

            return detail;
        }).collect(Collectors.toList());

        var order = entities.get(0).getOrderGuide();

        var user = userClient.getById(order.getCustomerId()).getBody();

        OrderDetailGuideResponse response = convertToOrderDetailResponse(detailResponse, order);

        response.setUser(user);

        response.setTotal(total);

        return response;
    }

    @Override
    @Transactional
    public OrderDetailGuideResponse create(OrderDetailRequest request) {

        OrderGuide orderGuide = new OrderGuide();

        orderGuide.setCustomerId(request.getUserId());
        orderGuide.setSaleCreated(new Date());

        var user = userClient.getById(request.getUserId()).getBody();

        if (user.getId() == null) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred in user microservice");
        }

        orderGuide.setUser(user);

        try {
            orderGuideRepository.save(orderGuide);
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while creating order guide");
        }

        orderGuideRepository.save(orderGuide);
        List<OrderDetailGuide> entities = new ArrayList<OrderDetailGuide>();

        List<Long> prices = new ArrayList<Long>();

        request.getDetail().forEach(detail -> {
            OrderDetailGuide orderDetailGuide = new OrderDetailGuide();
            OrderDetailGuideId orderDetailGuideId = new OrderDetailGuideId();

            var guide = guideClient.getById(detail.getGuideId()).getBody();
            var coach = coachClient.updateWallet(guide.getCoachId(), guide.getPoints() * -1).getBody();

            if (coach.getId() == null) {
                throw new ResourceNotFoundExceptionRequest("Error ocurred in coach microservice");
            }

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
            orderDetailGuide.setCoachId(guide.getCoachId());

            orderDetailGuideRepository.save(orderDetailGuide);

            // Guardamos todas las compras para pasarlo luego en un response
            entities.add(orderDetailGuide);
            prices.add(guide.getPoints());
        });

        var total = prices.stream().mapToLong(Long::longValue).sum();

        user = userClient.updateWallet(total, user.getId()).getBody();

        if (user.getId() == null) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred in user microservice");
        }

        var detail = entities.stream().map(entity -> convertToResponse(entity)).collect(Collectors.toList());

        var response = convertToOrderDetailResponse(detail, orderGuide);

        response.setTotal(total);
        response.setUser(user);

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

    @Override
    public CoachOrderDetailResponse getAllByCoachId(Long id) {

        var orders = orderDetailGuideRepository.getAllByCoachId(id);

        CoachOrderDetailResponse response = new CoachOrderDetailResponse();

        Coach coach = coachClient.geById(id).getBody();

        HashMap<Long, CoachGuideDetail> map = new HashMap<Long, CoachGuideDetail>();

        List<CoachGuideDetail> guideDetails = new ArrayList<CoachGuideDetail>();

        var sales = 0L;

        for (var i = 0; i < orders.size(); i++) {
            var order = orders.get(i);
            var guideId = order.getOrderDetailGuideId().getGuideId();
            if (!map.containsKey(guideId)) {
                CoachGuideDetail coachGuideDetail = new CoachGuideDetail();
                coachGuideDetail.setGuideId(guideId);
                coachGuideDetail.setQuantify(1L);
                map.put(guideId, coachGuideDetail);
            } else {
                var update = map.get(guideId);
                var quantify = update.getQuantify() + 1;
                update.setQuantify(quantify);
                map.put(guideId, update);
            }
            sales = sales + 1L;
        }

        for (Entry<Long, CoachGuideDetail> entry : map.entrySet()) {

            var detail = entry.getValue();

            var guide = guideClient.getById(detail.getGuideId()).getBody();

            detail.setGuide(guide);

            guideDetails.add(detail);
        }

        response.setGuideDetails(guideDetails);
        response.setSales(sales);
        response.setCoach(coach);
        response.setCoachId(id);

        return response;
    }

}
