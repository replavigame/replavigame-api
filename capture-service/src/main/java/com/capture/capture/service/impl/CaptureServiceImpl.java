package com.capture.capture.service.impl;

import java.util.Date;
import java.util.List;

import com.capture.capture.entity.Capture;
import com.capture.capture.repository.CaptureRepository;
import com.capture.capture.service.CaptureService;
import com.capture.exception.ResourceNotFoundExceptionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptureServiceImpl implements CaptureService {

    @Autowired
    private CaptureRepository captureRepository;

    @Override
    public Capture cancel(String token) {
        Capture entity = new Capture();
        entity.setDate(new Date());
        entity.setPayerId("none");
        entity.setStatus("CANCELED");
        entity.setPaymentId("none");
        entity.setToken("none");

        try {
            captureRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving capture");
        }
    }

    @Override
    public Capture accept(String paymentId, String token, String payerId) {
        Capture entity = new Capture();
        entity.setDate(new Date());
        entity.setPayerId(payerId);
        entity.setStatus("ACCEPTED");
        entity.setPaymentId(paymentId);
        entity.setToken(token);

        try {
            captureRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new ResourceNotFoundExceptionRequest("Error ocurred while saving capture");
        }
    }

    @Override
    public List<Capture> getAll() {
        var entities = captureRepository.findAll();
        return entities;
    }

}
