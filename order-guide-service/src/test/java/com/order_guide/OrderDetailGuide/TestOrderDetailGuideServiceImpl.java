package com.order_guide.OrderDetailGuide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.order_guide.exception.ResourceNotFoundExceptionRequest;
import com.order_guide.order_guide.client.CoachClient;
import com.order_guide.order_guide.client.GuideClient;
import com.order_guide.order_guide.client.UserClient;
import com.order_guide.order_guide.dto.DetailResponse;
import com.order_guide.order_guide.entity.OrderDetailGuide;
import com.order_guide.order_guide.entity.OrderDetailGuideId;
import com.order_guide.order_guide.entity.OrderGuide;
import com.order_guide.order_guide.model.Category;
import com.order_guide.order_guide.model.Coach;
import com.order_guide.order_guide.model.Guide;
import com.order_guide.order_guide.model.User;
import com.order_guide.order_guide.repository.OrderDetailGuideRepository;
import com.order_guide.order_guide.repository.OrderGuideRepository;
import com.order_guide.order_guide.service.impl.OrderDetailGuideServiceImpl;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class TestOrderDetailGuideServiceImpl {
    @Spy
    private OrderDetailGuideRepository orderDetailGuideRepository;

    @Spy
    private OrderGuideRepository orderGuideRepository;

    @Spy
    private GuideClient guideClient;

    @Spy
    private UserClient userClient;

    @Spy
    private CoachClient coachClient;

    @InjectMocks
    private OrderDetailGuideServiceImpl orderDetailGuideServiceImpl;

    OrderDetailGuide orderdetailguide;
    OrderDetailGuideId orderDetailGuideId;
    Coach coach;
    Guide guide;
    OrderGuide orderGuide;
    User user;
    Category category;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setCreatedDate(new Date());
        user.setId(1L);
        user.setLastName("Tulio");
        user.setName("Jorge");
        user.setPoints(40L);

        category = new Category();
        category.setGameId(1L);
        category.setId(1L);
        category.setName("Juegos MMR");

        orderDetailGuideId = new OrderDetailGuideId();
        orderDetailGuideId.setGuideId(1L);
        orderDetailGuideId.setOrderGuideId(1L);

        orderGuide = new OrderGuide();
        orderGuide.setCustomerId(1L);
        orderGuide.setId(1L);
        orderGuide.setSaleCreated(new Date());
        orderGuide.setUser(user);

        coach = new Coach();
        coach.setGameId(1L);
        coach.setId(1L);
        coach.setLastName("Lopez");
        coach.setName("Jose");
        coach.setNameCoach("Luciano");

        guide = new Guide();
        guide.setCategory(category);
        guide.setCoachId(1L);
        guide.setDescount(10.0);
        guide.setDescription("Nuevo curso de Test");
        guide.setGameId(1L);
        guide.setId(1L);
        guide.setPoints(20L);
        guide.setTitle("Jerarquias");

        orderdetailguide = new OrderDetailGuide();
        orderdetailguide.setCoach(coach);
        orderdetailguide.setCoachId(1L);
        orderdetailguide.setGuide(guide);
        orderdetailguide.setOrderDetailGuideId(orderDetailGuideId);
        orderdetailguide.setOrderGuide(orderGuide);
        orderdetailguide.setPrice(30L);
    }

    @Test
    @DisplayName("When Find All Then Return List Detail Guide")
    void WhenFindAllThenReturnListDetailGuide() {
        // Arrange
        List<OrderDetailGuide> list = new ArrayList<OrderDetailGuide>();
        list.add(orderdetailguide);
        when(orderDetailGuideRepository.findAll()).thenReturn(list);
        // Para cuando sea error y sea un dato normal
        // doThrow(ResourceNotFoundExceptionRequest.class).when(orderDetailGuideRepository).deleteAll();

        // Act
        List<DetailResponse> response = orderDetailGuideServiceImpl.getAll();

        // Assert
        assertEquals(1L, response.size());

    }

    // @Test
    // @DisplayName("Get All By Order Id Return List Detail Guide")
    // void GetAllByOrderIdReturnListDetailGuide(){}

    // @Test
    // @DisplayName("Create Order Detail Guide")
    // void CreateOrderDetailGuide(){}

    @Test
    @DisplayName("Delete Order Detail Guide")
    void DeleteOrderDetailGuide() {
        // Arrange
        doNothing().when(orderDetailGuideRepository).deleteById(orderDetailGuideId);
        doNothing().when(orderDetailGuideRepository).deleteAllByOrderGuideId(1L);

        // Act
        Throwable exception = catchThrowable(() -> {
            orderDetailGuideServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("Delete Order Detail Guide")
    void WhenDeleteAllByOrderGuideIdButErrorOcurredWhileDeleting() {
        // Arrange
        doNothing().when(orderDetailGuideRepository).deleteById(orderDetailGuideId);
        doThrow(ResourceNotFoundExceptionRequest.class).when(orderDetailGuideRepository).deleteAllByOrderGuideId(1L);

        // doThrow(ResourceNotFoundExceptionRequest.class).when(orderGuideRepository).deleteById(1L);

        // Act
        String message = "Error ocurred while deleting order guide";
        Throwable exception = catchThrowable(() -> {
            orderDetailGuideServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Delete Order Detail Guide")
    void WhenDeleteByIdButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(orderGuideRepository).deleteById(1L);
        doNothing().when(orderDetailGuideRepository).deleteAllByOrderGuideId(1L);

        // Act
        String message = "Error ocurred while deleting order guide";
        Throwable exception = catchThrowable(() -> {
            orderDetailGuideServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    // @Test
    // @DisplayName("Get All By Coach Id Return List Guide Detail")
    // void GetAllByCoachIdReturnListGuideDetail(){}
}