package com.coachingsession.Session;

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

import com.coachingsession.coaching_session.client.CoachClient;
import com.coachingsession.coaching_session.client.UserClient;
import com.coachingsession.coaching_session.dto.SessionResponse;
import com.coachingsession.coaching_session.entity.Session;
import com.coachingsession.coaching_session.models.Coach;
import com.coachingsession.coaching_session.models.User;
import com.coachingsession.coaching_session.repository.SessionRepository;
import com.coachingsession.coaching_session.service.impl.SessionServiceImpl;
import com.coachingsession.exception.ResourceNotFoundExceptionRequest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TestSessionServiceImpl {
    @Spy
    private SessionRepository repository;

    @Spy
    private CoachClient coachClient;

    @Spy
    private UserClient userClient;

    @InjectMocks
    private SessionServiceImpl sessionServiceImpl;

    Session session;
    User user;
    Coach coach;

    @BeforeEach
    void init (){
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

    }

    @Test
    @DisplayName("Get All Return List Session")
    void GetAllReturnListSession(){
        //Arrange
        List<Session> list = new ArrayList<Session>();
        list.add(session);
        when(repository.findAll()).thenReturn(list);

        //Act
        List<SessionResponse> response = sessionServiceImpl.getAll();

        //Assert
        assertEquals(1L, response.size());
    }

    //@Test
    //@DisplayName("Get By Id Return Session")
    //void GetByIdReturnSession(){}

    //@Test
    //@DisplayName("Create Session")
    //void CreateSession(){}

    //@Test
    //@DisplayName("Update Session")
    //void UpdateSession(){}

    @Test
    @DisplayName("Delete Session")
    void DeleteSession(){
        // Arrange
        doNothing().when(repository).deleteById(1L);

        // Act
        Throwable exception = catchThrowable(() -> {
            sessionServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isEqualTo(null);
    }

    @Test
    @DisplayName("When Delete An User But Error Ocurred While Deleting")
    public void WhenDeleteAnUserButErrorOcurredWhileDeleting() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(repository).deleteById(1L);

        // Act
        String message = "Error occurred while deleting Session";
        Throwable exception = catchThrowable(() -> {
            sessionServiceImpl.delete(1L);
        });

        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);

    }
}