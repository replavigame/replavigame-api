package com.order_game_coin.OrderGameCoin;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.order_game_coin.exception.ResourceNotFoundExceptionRequest;
import com.order_game_coin.order_game_coin.dto.OrderGameCoinRequest;
import com.order_game_coin.order_game_coin.dto.OrderGameCoinResponse;
import com.order_game_coin.order_game_coin.entity.OrderGameCoin;
import com.order_game_coin.order_game_coin.repository.OrderGameCoinRepository;
import com.order_game_coin.order_game_coin.service.impl.OrderGameCoinServiceImpl;


@DataJpaTest
public class OrderGameCoinServiceImplMockTest {

    @Spy
    private OrderGameCoinRepository orderGameCoinRepository;

    

    

    @InjectMocks
    private OrderGameCoinServiceImpl orderGameCoinServiceImpl;

    OrderGameCoin orderGameCoin;
    OrderGameCoin orderGameCoin2;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        orderGameCoin = new OrderGameCoin();
        orderGameCoin.setCustomerId(1L);
        orderGameCoin.setId(1L);
        orderGameCoin.setTotalPrice(12L);
        
    }

    @Test
    @DisplayName("When Find By Id Then Return OrderGameCoin")
    public void WhenFindByIdThenReturnOrderGameCoin() {
        // Arrange
        when(orderGameCoinRepository.getOrderGameById(1L)).thenReturn(Optional.of(orderGameCoin));

        // Act
        OrderGameCoinResponse response = orderGameCoinServiceImpl.getById(1L);

        // Assert
        assertEquals(1L, orderGameCoin.getId());
        assertEquals(1L, response.getCustomerId());
        assertEquals(12L, response.getTotalPrice());
  
    }

    @Test
    @DisplayName("When Find By Id But Not Exist Id")
    public void WhenFindByIdButNotExistId() {
        // Arrange
        when(orderGameCoinRepository.getOrderGameById(1L)).thenReturn(Optional.empty());

        // Act
        String message = "Order game coin not found";
        Throwable exception = catchThrowable(() -> {
            orderGameCoinServiceImpl.getById(2L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class)
                .hasMessageContaining(message);
  
    }

    @Test
    @DisplayName("When Create A OrderGameCoin Then Return OrderGameCoin")
    public void WhenCreateAOrderGameCoinThenReturnOrderGameCoin() {
        // Arrange
        OrderGameCoinRequest request = new OrderGameCoinRequest();

        request.setCustomerId(1L);
        request.setTotalPrice(12L);
    

     
        when(orderGameCoinRepository.save(Mockito.any())).thenReturn(orderGameCoin);
        // Act
        OrderGameCoinResponse response =  orderGameCoinServiceImpl.create(request);

        // Assert
        assertEquals(1L, orderGameCoin.getId());
        assertEquals(1L, response.getCustomerId());
        assertEquals(12L, response.getTotalPrice());
    }

    @Test
    @DisplayName("When Create A OrderGameCoin But Error Ocurred")
    public void WhenCreateAOrderGameCoinButErrorOcurred() {
        // Arrange
        OrderGameCoinRequest request = new OrderGameCoinRequest();

        request.setCustomerId(1L);
        request.setTotalPrice(12L);

        
        when(orderGameCoinRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        // Act
        String message = "Error ocurred while creating order game coin";
        Throwable exception = catchThrowable(() -> {
            orderGameCoinServiceImpl.create(request);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class)
                .hasMessageContaining(message);
 
}


    @Test
    @DisplayName("When Update A OrderGameCoin Return A OrderGameCoin")
    public void WhenUpdateAOrderGameCoinReturnAOrderGameCoin() {
        // Arrange
        OrderGameCoinRequest request = new OrderGameCoinRequest();
        
        request.setCustomerId(1L);
        request.setTotalPrice(12L);

        
        when(orderGameCoinRepository.save(Mockito.any())).thenReturn(orderGameCoin);
        when(orderGameCoinRepository.getOrderGameById(1L)).thenReturn(Optional.of(orderGameCoin));
        // Act
       
        OrderGameCoinResponse response =  orderGameCoinServiceImpl.update(request, 1L);


        // Assert

        assertEquals(1L, response.getId());
        assertEquals(1L, response.getCustomerId());
        assertEquals(12L, response.getTotalPrice());

    }
    @Test
    @DisplayName("When Update A OrderGameCoin But OrderGameCoin Not Found")
    public void WhenUpdateAOrderGameCoinButGameNotFound() {
        // Arrange
        OrderGameCoinRequest request = new OrderGameCoinRequest();

        request.setCustomerId(1L);
        request.setTotalPrice(12L);

        
        when(orderGameCoinRepository.save(Mockito.any())).thenReturn(orderGameCoin);
        
        // Arrange
        when(orderGameCoinRepository.getOrderGameById(2L)).thenReturn(Optional.empty());

        // Act
        String message = "Order game coin not found";
        Throwable exception = catchThrowable(() -> {
            orderGameCoinServiceImpl.update(request, 1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class)
                .hasMessageContaining(message);

    }

    @Test
    @DisplayName("When Update A OrderGameCoin But Error Ocurred")
    public void WhenUpdateAOrderGameCoinButErrorOcurred() {
        // Arrange
        OrderGameCoinRequest request = new OrderGameCoinRequest();

        request.setCustomerId(1L);
        request.setTotalPrice(12L);

        
        when(orderGameCoinRepository.getOrderGameById(1L)).thenReturn(Optional.of(orderGameCoin));

        when(orderGameCoinRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        // Act
        String message = "Error ocurred while updating order game coin";
        Throwable exception = catchThrowable(() -> {
            orderGameCoinServiceImpl.update(request, 1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);

    }



    @Test
    @DisplayName("When Delete A OrderGameCoin By Id Is Successful")
    public void WhenDeleteAOrderGameCoinByIdIsSuccessful() {
        // Arrange
        doNothing().when(orderGameCoinRepository).deleteById(1L);
        // Act
        Throwable exception = catchThrowable(() -> {
            orderGameCoinServiceImpl.delete(1L);
        });
        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("When Delete A OrderGameCoin But Error Ocurred While Deleting")
    public void WhenDeleteAOrderGameCoinButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(orderGameCoinRepository).deleteById(1L);
        // Act
        String message = "Error ocurred while deleting order game coin";
        Throwable exception = catchThrowable(() -> {
            orderGameCoinServiceImpl.delete(1L);
        });
        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }





}
