package com.capture.capture.service;

import java.util.List;

import com.capture.capture.entity.Capture;

public interface CaptureService {
    Capture cancel(String token);

    Capture accept(String paymentId, String token, String payerId);

    List<Capture> getAll();
}
