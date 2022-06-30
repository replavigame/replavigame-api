package com.capture.Capture;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.capture.capture.entity.Capture;
import com.capture.capture.repository.CaptureRepository;
import com.capture.capture.service.impl.CaptureServiceImpl;

@DataJpaTest
public class TestCaptureServiceImpl {
    @Spy
    private CaptureRepository captureRepository;

    @InjectMocks
    private CaptureServiceImpl captureServiceImpl;

    Capture capture;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);

        capture = new Capture();
        capture.setDate(new Date());
        capture.setId(1L);
        capture.setPayerId("1L");
        capture.setPaymentId("1L");
        capture.setStatus("Accepted");
        capture.setToken("Bitcoin");
    }

    @Test
    @DisplayName("Get All Return List Capture")
    void GetAllReturnListCapture(){
        //Arrange
        List<Capture> list = new ArrayList<Capture>();
        list.add(capture);
        when(captureRepository.findAll()).thenReturn(list);

        //Act
        List<Capture> response = captureServiceImpl.getAll();

        //Assert
        assertEquals(1L, response.size());
    }
}