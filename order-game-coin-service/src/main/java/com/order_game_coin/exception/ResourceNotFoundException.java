package com.order_game_coin.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceNotFoundException {
    private final String message;
    private final HttpStatus status;
}
