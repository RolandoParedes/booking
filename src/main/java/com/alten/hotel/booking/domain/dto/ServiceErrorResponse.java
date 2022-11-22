package com.alten.hotel.booking.domain.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ServiceErrorResponse {
	
	private HttpStatus status;
	
	private String message;

	public ServiceErrorResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}