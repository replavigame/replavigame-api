package com.checkout.checkout.service;

import java.util.List;

import com.checkout.checkout.dto.DetailCardRequest;
import com.checkout.checkout.dto.DetailCardResponse;
import com.checkout.checkout.dto.UserDetailCardResponse;

public interface DetailCardService {
    List<DetailCardResponse> getAll();

    UserDetailCardResponse getAllByUserId(Long id);

    DetailCardResponse getById(Long id);

    DetailCardResponse create(DetailCardRequest request);

    DetailCardResponse update(DetailCardRequest request, Long id);

    void delete(Long id);
}
