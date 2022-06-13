package com.detail_guide.DetailGuide;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

@DataJpaTest
public class DetailGuideServiceImplMockTest {
    @Spy
    private DetailGuideRepository detailGuideRepository;

    //@Spy
    //private ModelMapper mapper;

    @InjectMocks
    private DetailGuideServiceImpl detailGuideServiceImpl;


    DetailGuide detailGuide;
    DetailGuide detailGuide2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        detailGuide = new DetailGuide();
        detailGuide.setId(1L);
        detailGuide.setName("name");
        detailGuide.setVideoUrl("url1");
        detailGuide.setGuideId(1L);

        detailGuide2 = new DetailGuide();
        detailGuide2.setId(2L);
        detailGuide2.setName("name1");
        detailGuide2.setVideoUrl("url2");
        detailGuide2.setGuideId(2L);

    }

    @Test
    @DisplayName("When Find By Id Then Return Detail Guide")
    public void WhenFindByIdThenReturnDetailGuide() {
        //Arrange
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailGuide));


        //Act
        DetailGuideResponse response = detailGuideServiceImpl.getById(1L);


        //Assert
        assertEquals(1L, detailGuide.getId());
        assertEquals("name", response.getName());
        assertEquals("url1", response.getVideoUrl());
        assertEquals(1L, response.getGuideId());
    }


    @Test
    @DisplayName("When Find By Id But Not Exist Id")
    public void WhenFindByIdButNotExistId() {
        //Arrange
        when(detailGuideRepository.getDetailGuideById(3L)).thenReturn(Optional.empty());


        //Act
        String message = "Detail guide not found";
        Throwable exception = catchThrowable(()->{
            detailGuideServiceImpl.getById(3L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    
    @Test
    @DisplayName("When Create A Detail Guide Then Return Detail Guide")
    public void WhenCreateADetailGuideThenReturnDetailGuide() {
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setName("request");
        request.setGuideId(2L);
        request.setVideoUrl("videoUrl");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(detailGuide2);

        //Act
        DetailGuideResponse response = detailGuideServiceImpl.create(request);

        //Assert
        assertEquals(1L, detailGuide.getId());
        assertEquals("request", response.getName());
        assertEquals("videoUrl", response.getVideoUrl());
        assertEquals(2L, response.getGuideId());
    }

    @Test
    @DisplayName("When Create A Detail Guide Then Return Detail Guide Error")
    public void WhenCreateADetailGuideThenReturnDetailGuideError() {
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("request");
        request.setGuideId(2L);
        request.setVideoUrl("videoUrl");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);

        //Act
        String message = "Error ocurred while creating detail guide";
        Throwable exception = catchThrowable(()->{
            detailGuideServiceImpl.create(request);
        });
        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Update A Detail Guide Then Return Detail Guide")
    public void WhenUpdateADetailGuideThenReturnDetailGuide() {
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();

        request.setGuideId(1L);
        request.setName("request");
        request.setVideoUrl("videoUrl");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(detailGuide);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailGuide));
        //Act
        DetailGuideResponse response = detailGuideServiceImpl.update(request, 1L);

        
        //Assert
        
        assertEquals(1L,response.getId() );
        assertEquals("name", response.getName());
        assertEquals("url1", response.getVideoUrl());
    }

    @Test
    @DisplayName("When Update A Detail Guide But Detail Not Found")
    public void WhenUpdateADetailGuideButDetailNotFound() {
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("request");
        request.setVideoUrl("videoUrl");

       
        //Act
        String message = "Detail guide not found";
        Throwable exception = catchThrowable(()->{
           detailGuideServiceImpl.update(request, 4L);
;
        });
        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Update A Detail Guide But Error Updating")
    public void WhenUpdateADetailGuideButErrorUpdating() {
        //Arrange
        DetailGuideRequest request = new DetailGuideRequest();
        request.setGuideId(1L);
        request.setName("request");
        request.setGuideId(2L);
        request.setVideoUrl("videoUrl");

        when(detailGuideRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        when(detailGuideRepository.getDetailGuideById(1L)).thenReturn(Optional.of(detailGuide));

        //Act
        String message = "Error ocurred while updating detail guide";
        Throwable exception = catchThrowable(()->{
            detailGuideServiceImpl.update(request, 1L);
        });
        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }
    @Test
    @DisplayName("When Delete A Detail Guide Is Successful")
    public void WhenDeleteADetailGuideIsSuccessful() {
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
    @DisplayName("When Delete A Detail Guide But Error Ocurred While Deleting")
    public void WhenDeleteAnCoachButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(detailGuideRepository).deleteById(1L);
        // Act
        String message = "Error ocurred while deleting detail guide";
        Throwable exception = catchThrowable(() -> {
            detailGuideServiceImpl.delete(1L);
        });
        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }


    
  
}
