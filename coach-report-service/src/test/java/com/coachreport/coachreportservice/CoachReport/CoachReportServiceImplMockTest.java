package com.coachreport.coachreportservice.CoachReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import java.util.Date;

import com.coachreport.coach_report.client.CoachClient;
import com.coachreport.coach_report.dto.CoachReportRequest;
import com.coachreport.coach_report.entity.CoachDocument;
import com.coachreport.coach_report.entity.CoachReport;
import com.coachreport.coach_report.models.Coach;
import com.coachreport.coach_report.repository.CoachDocumentRepository;
import com.coachreport.coach_report.repository.CoachReportRepository;
import com.coachreport.coach_report.service.impl.CoachReportServiceImpl;
import com.coachreport.exception.ResourceNotFoundExceptionRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import feign.Feign;

@DataJpaTest
public class CoachReportServiceImplMockTest {

    @Spy
    private CoachReportRepository coachReportRepository;

    @Spy
    private CoachDocumentRepository coachDocumentRepository;

    @Spy
    private CoachClient coachClient;

    @InjectMocks
    private CoachReportServiceImpl coachReportServiceImpl;

    private Coach coach;
    private CoachDocument coachDocument;
    private CoachReport coachReport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        coach = new Coach();
        coach.setGameId(1L);
        coach.setId(1L);
        coach.setLastName("lastName");
        coach.setName("name");
        coach.setNameCoach("nameCoach");

        coachReport = new CoachReport();
        coachReport.setApproved(true);
        coachReport.setCoach(coach);
        coachReport.setCoachId(1L);
        coachReport.setId(1L);
        coachReport.setObservation("Observacion sobre tu partida y recomiendo mejorar en el farmeo");
        coachReport.setQualifiedAt(new Date());
        coachReport.setReceivedAt(new Date());

        coachDocument = new CoachDocument();
        coachDocument.setCoachReport(coachReport);
        coachDocument.setComment("Ninguno");
        coachDocument.setDocumentUrl("https://www.youtube.com/watch?v=ie3mbivU7QQ");
        coachDocument.setId(1L);
        coachDocument.setTitle("title");
    }


    @Test
    @DisplayName("When Find By Id Then Return CoachReport")
    void WhenFindByIdThenReturnCoachReport() {
        // Arrange
        ResponseEntity<Coach> a = new ResponseEntity<>(coach, HttpStatus.OK);

        CoachReportRequest request = new CoachReportRequest();
        request.setApproved(true);
        request.setCoachId(1L);
        request.setObservation("Observacion sobre tu partida y recomiendo mejorar en el farmeo");
        request.setQualifiedAt(new Date());

        when(coachReportRepository.save(Mockito.any())).thenReturn(coachReport);
        when(coachClient.geById(1L)).thenReturn(a);
        // Act

        var response = coachClient.geById(1L).getBody();

        // Assert
        assertEquals(1L, response.getGameId());
    }


    @Test
    @DisplayName("When Find By Id Then Return Error")
    void WhenFindByIdThenReturnError() {
        // Arrange
        ResponseEntity<Coach> a = new ResponseEntity<>(coach, HttpStatus.OK);

        // Act
        String message = "CoachReport not found";
        Throwable exception = catchThrowable(() -> {
            coachReportServiceImpl.getById(6L);
        });
        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    @Test
    @DisplayName("When Update A CoachReport But Not Found")
    public void WhenUpdateACoachReportButNotFound() {
        // Arrange
        // Arrange
        ResponseEntity<Coach> a = new ResponseEntity<>(coach, HttpStatus.OK);

        CoachReportRequest request = new CoachReportRequest();
        request.setApproved(true);
        request.setCoachId(1L);
        request.setObservation("Observacion sobre tu partida y recomiendo mejorar en el farmeo");
        request.setQualifiedAt(new Date());
        doThrow(ResourceNotFoundExceptionRequest.class).when(coachReportRepository).deleteById(1L);
        // Act
        String message = "CoachReport not found";
        Throwable exception = catchThrowable(() -> {
            coachReportServiceImpl.update(request, 2L);
        });
        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }

    

    @Test
    @DisplayName("When Delete A CoachReport But Not Found")
    public void WhenDeleteACoachReportButNotFound() {
        // Arrange
        doThrow(ResourceNotFoundExceptionRequest.class).when(coachReportRepository).deleteById(1L);
        // Act
        String message = "CoachReport not found";
        Throwable exception = catchThrowable(() -> {
            coachReportServiceImpl.delete(1L);
        });
        // Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundExceptionRequest.class).hasMessage(message);
    }


    
}
