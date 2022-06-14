package com.coachingsession.Platform;

import java.util.ArrayList;
import java.util.Date;
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

import com.coachingsession.coaching_session.dto.PlatformRequest;
import com.coachingsession.coaching_session.dto.PlatformResponse;
import com.coachingsession.coaching_session.entity.Platform;
import com.coachingsession.coaching_session.entity.Session;
import com.coachingsession.coaching_session.models.Coach;
import com.coachingsession.coaching_session.models.User;
import com.coachingsession.coaching_session.repository.PlatformRepository;
import com.coachingsession.coaching_session.repository.SessionRepository;
import com.coachingsession.coaching_session.service.impl.PlatformServiceImpl;
import com.coachingsession.exception.ResourceNotFoundExceptionRequest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestPlatformServiceImpl {
    @Spy
    private PlatformRepository platformRepository;

    @Spy
    private SessionRepository sessionRepository;

    @InjectMocks
    private PlatformServiceImpl platformServiceImpl;

    Platform platform;
    Platform platform2;
    Session session;
    Coach coach;
    User user;
    
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        coach = new Coach();
        coach.setGameId(1L);
        coach.setId(1L);
        coach.setLastName("Lopez");
        coach.setName("Jose");
        coach.setNameCoach("Luciano");
        
        user = new User();
        user.setId(1L);
        user.setLastName("Tulio");
        user.setName("Jorge");
        user.setNumber("980987630");
        
        session = new Session();
        session.setAvailable(true);
        session.setCoach(coach);
        session.setCoachId(1L);
        session.setEndDate(new Date());
        session.setId(1L);
        session.setName("Sesion 01-Dota");
        session.setStartDate(new Date());
        session.setUser(user);
        session.setUserId(1L);
        
        platform = new Platform();
        platform.setId(1L);
        platform.setName("Zoom");
        platform.setSession(session);
        platform.setUrl("https://zoom.us/");

        platform2 = new Platform();
        platform2.setId(1L);
        platform2.setName("Meets");
        platform2.setSession(session);
        platform2.setUrl("https://Meets.us/");
    }

    @Test
    @DisplayName("Get All Return List Platform")
    void GetAllReturnListPlatform(){
        //Arrange
        List<Platform> list = new ArrayList<Platform>();
        list.add(platform);
        when(platformRepository.findAll()).thenReturn(list);

        //Act
        List<PlatformResponse> response = platformServiceImpl.getAll();

        //Assert
        assertEquals(1L, response.size());
    }

    @Test
    @DisplayName("Get By Id Return Platform")
    void GetByIdReturnPlatform(){
        // Arrange
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.of(platform));
        // Act
        PlatformResponse response = platformServiceImpl.getById(1L);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Zoom", response.getName());
        assertEquals("https://zoom.us/", response.getUrl());
    }

    @Test
    @DisplayName("When Find By Id But Not Exist Id")
    public void WhenFindByIdButNotExistId() {
        // Arrange
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.empty());
        // Act
        String message = "Platform not found";
        Throwable exception = catchThrowable(() -> {
            platformServiceImpl.getById(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Create Platform")
    void CreatePlatform(){
        //Arrange
        PlatformRequest request = new PlatformRequest();
        request.setName("Teams");
        request.setUrl("https://Teams.us/");
        request.setSession_id(1L);

        when(platformRepository.save(Mockito.any())).thenReturn(platform2);
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.of(platform));

        //Act
        PlatformResponse response = platformServiceImpl.create(request);

        //Assert
        assertEquals("Teams", response.getName());
        assertEquals("https://Teams.us/", response.getUrl());
        assertEquals(1L, response.getSession_id());
    }

    @Test
    @DisplayName("When Create A Guide But Error Ocurred While Creating")
    public void WhenCreateAGuideButErrorOcurredWhileCreating() {
        //Arrange
        PlatformRequest request = new PlatformRequest();
        request.setName("Teams");
        request.setUrl("https://Teams.us/");

        when(platformRepository.save(Mockito.any())).thenReturn(platform2);
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.of(platform));

        //Act
        final PlatformRequest finalRequest = request;
        String message = "Error ocurred while creating detail guide";
        Throwable exception = catchThrowable(() -> {
            platformServiceImpl.create(finalRequest);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Update Platform")
    void UpdatePlatform(){
        //Arrange
        PlatformRequest request = new PlatformRequest();
        request.setName("Teams");
        request.setUrl("https://Teams.us/");

        when(platformRepository.save(Mockito.any())).thenReturn(platform2);
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.of(platform));

        //Act
        PlatformResponse response = platformServiceImpl.update(request, 1L);

        //Assert
        assertEquals(1L, response.getId());
        assertEquals("Teams", response.getName());
        assertEquals("https://Teams.us/", response.getUrl());
    }

    @Test
    @DisplayName("Update Platform")
    void WhenUpdateAGuideButDetailGuideNotFound(){
        //Arrange
        PlatformRequest request = new PlatformRequest();
        platform2.setId(1L);
        platform2.setName("Teams");
        platform2.setSession(session);
        platform2.setUrl("https://Teams.us/");

        when(platformRepository.save(Mockito.any())).thenReturn(platform2);
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.empty());

        //Act
        final PlatformRequest finalRequest = request;
        String message = "Platform not found";
        Throwable exception = catchThrowable(() -> {
            platformServiceImpl.update(finalRequest, 1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Update Platform")
    void WhenUpdateAGuideButErrorOcurredWhileUpdating(){
        //Arrange
        PlatformRequest request = new PlatformRequest();
        platform2.setId(1L);
        platform2.setName("Teams");
        platform2.setSession(session);
        platform2.setUrl("https://Teams.us/");

        when(platformRepository.save(Mockito.any())).thenReturn(ResourceNotFoundExceptionRequest.class);
        when(platformRepository.getPlatformById(1L)).thenReturn(Optional.of(platform));

        //Act
        final PlatformRequest finalRequest = request;
        String message = "Error occurred while creating platform";
        Throwable exception = catchThrowable(() -> {
            platformServiceImpl.update(finalRequest, 1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("Delete Platform")
    void DeletePlatform(){
        // Arrange
        doNothing().when(platformRepository).deleteById(1L);

        // Act
        Throwable exception = catchThrowable(() -> {
            platformServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("When Delete An User But Error Ocurred While Deleting")
    public void WhenDeleteAnUserButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(platformRepository).deleteById(1L);

        // Act
        String message = "Error occurred while deleting platform";
        Throwable exception = catchThrowable(() -> {
            platformServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);

    }
}