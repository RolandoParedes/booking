package com.alten.hotel.booking.domain.port.repository;

import java.time.LocalDate;
import java.util.List;

import com.alten.hotel.booking.domain.entity.BookingEntity;

public interface IBookingRepository {
	
	public BookingEntity saveBooking(BookingEntity bookingEntity);
	
	public List<BookingEntity> getBookingAvaibility(int maxpersons, LocalDate arrivalDate, LocalDate departureDate, Integer start,
												Integer limit);
	
	public BookingEntity findBookingByCodeAndClientId(int bookingCode, int clientId);
	
	public void deleteBookingCodeById(int idBooking);
	
	public BookingEntity findAvailableRoomAnotherDate(int idRoom, LocalDate arrivalDate, LocalDate departureDate);

}
