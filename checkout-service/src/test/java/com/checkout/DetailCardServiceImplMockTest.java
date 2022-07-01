package com.checkout;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.checkout.checkout.client.UserClient;
import com.checkout.checkout.dto.DetailCardRequest;
import com.checkout.checkout.dto.DetailCardResponse;
import com.checkout.checkout.entity.DetailCard;
import com.checkout.checkout.repository.DetailCardRepository;
import com.checkout.checkout.service.impl.DetailCardServiceImpl;
import com.checkout.exception.ResourceNotFoundExceptionRequest;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.util.Optional;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


@DataJpaTest
public class DetailCardServiceImplMockTest {


    @Spy
    private DetailCardRepository detailCardRepository;

    @InjectMocks
    private DetailCardServiceImpl detailCardServiceImpl;

    DetailCard detailCard;

    UserClient user;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);
        detailCard = new DetailCard();
        detailCard.setId(1L);
        detailCard.setCardholder("cardholder");
        detailCard.setCode(1L);
        detailCard.setMonth(1L);
        detailCard.setDay(1L);
        detailCard.setObfuscateCard(1L);
        detailCard.setUserId(1L);


    }

    @Test
    @DisplayName("When Find By Id Then Return Detail Card")
    public void WhenFindByIdThenReturnDetailCard() {
        //Arrange
        when(detailCardRepository.getDetailCardById(1L)).thenReturn(Optional.of(detailCard));


        //Act
        DetailCardResponse response = detailCardServiceImpl.getById(1L);


        //Assert
        assertEquals(1L, response.getId());
        assertEquals("cardholder", response.getCardholder());
        assertEquals(1L, response.getCode());
        assertEquals(1L, response.getDay());
        assertEquals(1L, response.getMonth());
        assertEquals(1L, response.getObfuscatedCard());
        assertEquals(1L, response.getUserId());
    }


    @Test
    @DisplayName("When Find By Id But Not Exist Id")
    public void WhenFindByIdButNotExistId() {
        //Arrange
        when(detailCardRepository.getDetailCardById(3L)).thenReturn(Optional.empty());

        //Act
        String message = "Card not found";
        Throwable exception = catchThrowable(()->{
            detailCardServiceImpl.getById(3L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Create A Detail Card Then Return Detail Card")
    public void WhenCreateADetailCardThenReturnDetailCard() {
        //Arrange
        DetailCardRequest request = new DetailCardRequest();
        request.setUserId(1L);
        request.setCardholder("cardholder");
        request.setCode(2L);
        request.setDay(2L);
        request.setMonth(2L);
        request.setObfuscatedCard(2L);

        when(detailCardRepository.save(Mockito.any())).thenReturn(detailCard);

        //Act
        //DetailCardResponse response = detailCardServiceImpl.create(request);

        //Assert
        //assertEquals(1L, response.getId());
        //assertEquals("cardholder", response.getCardholder());
        //assertEquals(2L, response.getCode());
        //assertEquals(2L, response.getDay());
        //assertEquals(2L, response.getMonth());
        //assertEquals(2L, response.getObfuscatedCard());
    }

    @Test
    @DisplayName("When Update A Detail Card But Card Not Found")
    public void WhenUpdateADetailCardButCardNotFound() {
        //Arrange
        DetailCardRequest request = new DetailCardRequest();
        request.setCode(3L);
        request.setDay(1L);
        request.setMonth(1L);

       
        //Act
        String message = "Card not found";
        Throwable exception = catchThrowable(()->{
           detailCardServiceImpl.update(request, 4L);
;
        });
        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }






    @Test
    @DisplayName("When Delete A Detail Card Is Successful")
    public void WhenDeleteADetailCardIsSuccessful() {
        // Arrange
        doNothing().when(detailCardRepository).deleteById(1L);
        // Act
        Throwable exception = catchThrowable(() -> {
            detailCardServiceImpl.delete(1L);
        });
        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("When Delete A Detail Card But Error Ocurred While Deleting")
    public void WhenDeleteACardButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(detailCardRepository).deleteById(1L);
        // Act
        String message = "Error ocurred while deleting card";
        Throwable exception = catchThrowable(() -> {
            detailCardServiceImpl.delete(1L);
        });
        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }  
}
