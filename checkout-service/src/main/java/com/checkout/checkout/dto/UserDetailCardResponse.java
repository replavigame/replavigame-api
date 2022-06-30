package com.checkout.checkout.dto;

import java.util.List;

import com.checkout.checkout.model.User;

import lombok.Data;

@Data
public class UserDetailCardResponse {
    List<DetailCardResponse> detailCardResponse;
    User user;
}
