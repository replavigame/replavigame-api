package com.detail_guide.DetailGuide;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.detail_guide.detail_guide.dto.DetailGuideRequest;
import com.detail_guide.detail_guide.dto.DetailGuideResponse;
import com.detail_guide.detail_guide.entity.DetailGuide;
import com.detail_guide.detail_guide.repository.DetailGuideRepository;
import com.detail_guide.detail_guide.service.impl.DetailGuideServiceImpl;
import com.detail_guide.exception.ResourceNotFoundExceptionRequest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TestDetailGuideServiceImpl {
    @Spy
    private DetailGuideRepository detailGuideRepository;

    @InjectMocks
    private DetailGuideServiceImpl detailGuideServiceImpl;
    
    DetailGuide detailguide;
    DetailGuide detailguide1;
    
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);

        detailguide = new DetailGuide();
        detailguide.setGuideId(1L);
        detailguide.setId(1L);
        detailguide.setName("Habilidades de Jungla");
        detailguide.setPosition(3L);
        detailguide.setVideoUrl("https://www.youtube.com/watch?v=BySqcdS2e-A");

        detailguide1 = new DetailGuide();
        detailguide1.setGuideId(1L);
        detailguide1.setId(1L);
        detailguide1.setName("Habilidades de Jungla1");
        detailguide1.setPosition(5L);
        detailguide1.setVideoUrl("https://www.facebook.com/watch?v=BySqcdS2e-A");
    }

    @Test
    @DisplayName("Get All Return List Detail Guide")
    void GetAllReturnListDetailGuide(){
        //Arrange
        List<DetailGuide> list = new ArrayList<DetailGuide>();
        list.add(detailguide);
        when(detailGuideRepository.findAll()).thenReturn(list);

        //Act
        List<DetailGuideResponse> response = detailGuideServiceImpl.getAll();

        //Assert
        assertEquals(1L, response.size());
    }

    @Test
    @DisplayName("Get By Id Return Detail Guide")
    void GetByIdReturnDetailGuide(){
        // Arrange
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailguide));
        // Act
        DetailGuideResponse response = detailGuideServiceImpl.getById(1L);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getGuideId());
        assertEquals("Habilidades de Jungla", response.getName());
        assertEquals(3L, response.getPosition());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist Id")
    public void WhenFindByIdButNotExistId() {
        // Arrange
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.empty());
        // Act
        String message = "Detail guide not found";
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.getById(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Create Detail Guide")
    void CreateDetailGuide(){
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("Habilidades de Jungla2");
        request.setPosition(7L);
        request.setVideoUrl("https://www.twitter.com/watch?v=BySqcdS2e-A");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(detailguide1);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailguide));

        //Act
        DetailGuideResponse response = detailGuideServiceImpl.create(request);

        //Assert
        assertEquals("Habilidades de Jungla2", response.getName());
        assertEquals(7L, response.getPosition());
        assertEquals("https://www.twitter.com/watch?v=BySqcdS2e-A", response.getVideoUrl());
    }

    @Test
    @DisplayName("When Create A Guide But Error Ocurred While Creating")
    public void WhenCreateAGuideButErrorOcurredWhileCreating() {
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("Habilidades de Jungla2");
        request.setPosition(7L);
        request.setVideoUrl("https://www.twitter.com/watch?v=BySqcdS2e-A");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailguide));

        //Act
        final DetailGuideRequest finalRequest = request;
        String message = "Error ocurred while creating detail guide";
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.create(finalRequest);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Update Detail Guide")
    void UpdateDetailGuide(){
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("Habilidades de Jungla2");
        request.setPosition(7L);
        request.setVideoUrl("https://www.twitter.com/watch?v=BySqcdS2e-A");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(detailguide1);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailguide));

        //Act
        DetailGuideResponse response = detailGuideServiceImpl.update(request, 1L);

        //Assert
        assertEquals(1L, response.getId());
        assertEquals("Habilidades de Jungla2", response.getName());
        assertEquals(7L, response.getPosition());
        assertEquals("https://www.twitter.com/watch?v=BySqcdS2e-A", response.getVideoUrl());
    }

    @Test
    @DisplayName("Update Detail Guide")
    void WhenUpdateAGuideButDetailGuideNotFound(){
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("Habilidades de Jungla2");
        request.setPosition(7L);
        request.setVideoUrl("https://www.twitter.com/watch?v=BySqcdS2e-A");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(detailguide1);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.empty());

        //Act
        final DetailGuideRequest finalRequest = request;
        String message = "Detail guide not found";
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.update(finalRequest, 1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Update Detail Guide")
    void WhenUpdateAGuideButErrorOcurredWhileUpdating(){
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("Habilidades de Jungla2");
        request.setPosition(7L);
        request.setVideoUrl("https://www.twitter.com/watch?v=BySqcdS2e-A");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailguide));

        //Act
        final DetailGuideRequest finalRequest = request;
        String message = "Error ocurred while updating detail guide";
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.update(finalRequest, 1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Delete Detail Guide")
    void DeleteDetailGuide(){
        // Arrange
        doNothing().when(detailGuideRepository).deleteById(1L);

        // Act
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("Delete Order Detail Guide")
    void WhenDeleteByIdButErrorOcurredWhileDeleting(){
        //Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(detailGuideRepository).deleteById(1L);
        
        //Act
        String message = "Error ocurred while deleting detail guide";
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.delete(1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Get All By Guide Id Return List Detail Guide")
    void GetAllByGuideIdReturnListDetailGuide(){
        //Arrange
        List<DetailGuide> list = new ArrayList<DetailGuide>();
        list.add(detailguide);
        when(detailGuideRepository.findAllByGuideId(1L)).thenReturn(list);

        //Act
        List<DetailGuideResponse> response = detailGuideServiceImpl.getAllByGuideId(1L);

        //Assert
        assertEquals(1L, response.size());
    }
}