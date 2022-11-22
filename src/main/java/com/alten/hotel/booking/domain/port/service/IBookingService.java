package com.alten.hotel.booking.domain.port.service;

import java.time.LocalDate;

import com.alten.hotel.booking.domain.dto.BookingReqDto;

public interface IBookingService {
	
	public BookingReqDto saveBooking(BookingReqDto bookingReqDto);
	
	public BookingReqDto findBookingByCodeAndClientId(BookingReqDto bookingReqDto);
	
	public void deleteBookingById(BookingReqDto bookingReqDto);
	
	public BookingReqDto findAvailableRoomAnotherDate(int idRoom, LocalDate arrivaldate, LocalDate departureDate);
	
	public BookingReqDto updateBooking(BookingReqDto bookingReqDto);

}
