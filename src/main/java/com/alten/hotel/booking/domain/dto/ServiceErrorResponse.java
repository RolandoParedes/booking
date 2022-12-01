package com.alten.hotel.booking.domain.dto;

import org.springframework.http.HttpStatus;

public record ServiceErrorResponse(HttpStatus status, String message) {}