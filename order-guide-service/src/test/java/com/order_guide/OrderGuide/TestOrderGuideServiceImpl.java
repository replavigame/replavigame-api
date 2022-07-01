package com.order_guide.OrderGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.order_guide.exception.ResourceNotFoundExceptionRequest;
import com.order_guide.order_guide.dto.OrderGuideRequest;
import com.order_guide.order_guide.dto.OrderGuideResponse;
import com.order_guide.order_guide.entity.OrderGuide;
import com.order_guide.order_guide.model.User;
import com.order_guide.order_guide.repository.OrderGuideRepository;
import com.order_guide.order_guide.service.impl.OrderGuideServiceImpl;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class TestOrderGuideServiceImpl {
    @Spy
    private OrderGuideRepository orderGuideRepository;

    @InjectMocks
    private OrderGuideServiceImpl orderGuideServiceImpl;

    OrderGuide orderguide;
    OrderGuide orderguide1;
    User user;
    User user2;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setCreatedDate(new Date());
        user.setId(1L);
        user.setLastName("Tulio");
        user.setName("Jorge");
        user.setPoints(40L);

        user2 = new User();
        user2.setCreatedDate(new Date());
        user2.setId(2L);
        user2.setLastName("Lopez");
        user2.setName("Jose");
        user2.setPoints(30L);

        orderguide = new OrderGuide();
        orderguide.setCustomerId(1L);
        orderguide.setId(1L);
        orderguide.setSaleCreated(new Date());
        orderguide.setUser(user);

        orderguide1 = new OrderGuide();
        orderguide1.setCustomerId(2L);
        orderguide1.setId(1L);
        orderguide1.setSaleCreated(new Date());
        orderguide1.setUser(user2);
    }

    @Test
    @DisplayName("Get All Return List Order Guide")
    void GetAllReturnListOrderGuide() {
        // Arrange
        List<OrderGuide> list = new ArrayList<OrderGuide>();
        list.add(orderguide);
        when(orderGuideRepository.findAll()).thenReturn(list);

        // Act
        List<OrderGuideResponse> response = orderGuideServiceImpl.getAll();

        // Assert
        assertEquals(1L, response.size());
    }

    @Test
    @DisplayName("Get All By Costumer Id Return List Order Guide")
    void GetAllByCostumerIdReturnListOrderGuide() {
        // Arrange
        List<OrderGuide> list = new ArrayList<OrderGuide>();
        list.add(orderguide);
        when(orderGuideRepository.findAllByCustomerId(1L)).thenReturn(list);

        // Act
        List<OrderGuideResponse> response = orderGuideServiceImpl.getAllByCustomerId(1L);

        // Assert
        assertEquals(1L, response.size());
    }

    @Test
    @DisplayName("Get By Id Return Order Guide")
    void GetByIdReturnOrderGuide() {
        // Arrange
        when(orderGuideRepository.getOrderGuideById(1L)).thenReturn(Optional.of(orderguide));

        // Act
        OrderGuideResponse response = orderGuideServiceImpl.getById(1L);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getUserId());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist Id")
    void WhenFindByIdButNotExistId() {
        // Arrange
        when(orderGuideRepository.getOrderGuideById(1L)).thenReturn(Optional.empty());

        // Act
        String message = "Order guide not found";
        Throwable exception = catchThrowable(() -> {
            orderGuideServiceImpl.getById(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Create Order Guide")
    void CreateOrderGuide(){
        //Arrange
        OrderGuideRequest request = new OrderGuideRequest();
        request.setCustomerId(2L);
        request.setSaleCreated(new Date());
        request.setUser(user2);

        when(orderGuideRepository.save(Mockito.any())).thenReturn(orderguide1);
        when(orderGuideRepository.getOrderGuideById(1L)).thenReturn(Optional.of(orderguide));
        //Act
        OrderGuideResponse response = orderGuideServiceImpl.create(request);
        //Assert
        assertEquals(2L, response.getCustomerId());
    }

    @Test
    @DisplayName("When Create A Guide But Error Ocurred While Creating")
    void WhenCreateButErrorOcurredWhileCreating(){
        //Arrange
        OrderGuideRequest request = new OrderGuideRequest();
        request.setCustomerId(2L);
        request.setSaleCreated(new Date());
        request.setUser(user2);

        when(orderGuideRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        when(orderGuideRepository.getOrderGuideById(1L)).thenReturn(Optional.of(orderguide));

        //Act
        final OrderGuideRequest finalRequest = request;
        String message = "Error ocurred while creating order guide";
        Throwable exception = catchThrowable(() -> {
            orderGuideServiceImpl.create(finalRequest);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Update Order Guide")
    void UpdateOrderGuide(){
        //Arrange
        OrderGuideRequest request = new OrderGuideRequest();
        request.setCustomerId(2L);
        request.setSaleCreated(new Date());
        request.setUser(user2);

        when(orderGuideRepository.save(Mockito.any())).thenReturn(orderguide1);
        when(orderGuideRepository.getOrderGuideById(1L)).thenReturn(Optional.of(orderguide));
        //Act
        OrderGuideResponse response = orderGuideServiceImpl.update(request, 1L)
        //Assert
        assertEquals(2L, response.getCustomerId());
    }

    @Test
    @DisplayName("When Update A OrderGuide But Error Ocurred While Creating")
    void WhenUpdateAOrderGuideButErrorOcurredWhileUpdating(){
        //Arrange
        OrderGuideRequest request = new OrderGuideRequest();
        request.setCustomerId(2L);
        request.setSaleCreated(new Date());
        request.setUser(user2);

        when(orderGuideRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        when(orderGuideRepository.getOrderGuideById(1L)).thenReturn(Optional.of(orderguide));

        //Act
        final OrderGuideRequest finalRequest = request;
        String message = "Error ocurred while updating order guide";
        Throwable exception = catchThrowable(() -> {
            orderGuideServiceImpl.update(finalRequest);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Delete Order Guide")
    void DeleteOrderGuide() {
        // Arrange
        doNothing().when(orderGuideRepository).deleteById(1L);

        // Act
        Throwable exception = catchThrowable(() -> {
            orderGuideServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("Delete Order Guide")
    void WhenDeleteByIdButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(orderGuideRepository).deleteById(1L);

        // Act
        String message = "Error ocurred while deleting order guide";
        Throwable exception = catchThrowable(() -> {
            orderGuideServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }
}