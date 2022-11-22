package com.alten.hotel.booking.infraestructure.adapter.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.entity.BookingEntity;
import com.alten.hotel.booking.domain.port.repository.IBookingRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.jpa.BookingRepositoryJPA;

@Component
public class BookingRepository implements IBookingRepository{
	
	@Autowired
	private BookingRepositoryJPA repository;

	@Override
	public BookingEntity saveBooking(BookingEntity bookingEntity) {
		this.repository.save(bookingEntity);
		return bookingEntity;
	}

	@Override
	public List<BookingEntity> getBookingAvaibility(int maxpersons, LocalDate arrivalDate, LocalDate departureDate,
			Integer start, Integer limit) {
		Pageable pageable = PageRequest.of(start, limit);
		return this.repository.getAvailableRooms(maxpersons, departureDate, arrivalDate, pageable);
	}

	@Override
	public BookingEntity findBookingByCodeAndClientId(int bookingCode, int clientId) {
		return this.repository.findBookingByCodeAndClientId(bookingCode, clientId);
	}

	@Override
	public void deleteBookingCodeById(int idBooking) {
		this.repository.deleteById(idBooking);
	}

	@Override
	public BookingEntity findAvailableRoomAnotherDate(int idRoom, LocalDate arrivalDate, LocalDate departureDate) {
		return this.repository.findAvailableRoomAnotherDate(idRoom, arrivalDate, departureDate);
	}

}
