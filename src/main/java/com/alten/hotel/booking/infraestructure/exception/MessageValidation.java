package com.alten.hotel.booking.infraestructure.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.alten.hotel.booking.domain.dto.ApiResponse;
import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.util.CommonUtils;

public class MessageValidation {
	
	private MessageValidation() {}
	
	private static final String ERROR = "ERROR";
	
	public static Map<String,ApiResponse> validateBooking(BookingReqDto bookingReqDto) {
		
		Map<String,ApiResponse> mapMessage = new HashMap<>();
		
		if(CommonUtils.evaluateMaxRangeDaysBooked.test(bookingReqDto)) {
			mapMessage.put(ERROR, new ApiResponse<>("Reservation can not be 30 days in advance", HttpStatus.FORBIDDEN.toString(), null));
			return mapMessage;
		}
		else if(CommonUtils.evaluateMaxDaysBooked.test(bookingReqDto)) {
			mapMessage.put(ERROR, new ApiResponse<>("Reservation can not be more than 3 days", HttpStatus.FORBIDDEN.toString(), null));
			return mapMessage;
			
		}else if(CommonUtils.evaluatebookingDateDifferArrivalDate.test(bookingReqDto)) {
			mapMessage.put(ERROR, new ApiResponse<>("Reservation can not start the same day as today", HttpStatus.FORBIDDEN.toString(), null));
			return mapMessage;
		}else if(CommonUtils.validateNotsameDate.test(bookingReqDto)) {
			mapMessage.put(ERROR, new ApiResponse<>("Reservation arrival date can not be the same as departure date", HttpStatus.FORBIDDEN.toString(), null));
			return mapMessage;
		}else if(CommonUtils.evaluateArrivalDateDepartureDate.test(bookingReqDto)) {
			mapMessage.put(ERROR, new ApiResponse<>("Arrival Date can not be after departure date", HttpStatus.FORBIDDEN.toString(), null));
			return mapMessage;
		}
		else if(CommonUtils.evaluateDayAfterToday.test(bookingReqDto)) {
			mapMessage.put(ERROR, new ApiResponse<>("Arrival Date can not be before today", HttpStatus.FORBIDDEN.toString(), null));
			return mapMessage;
		}
		
		return mapMessage;
	}

}
