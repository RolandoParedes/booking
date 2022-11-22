package com.alten.hotel.booking.domain.port.repository;

import java.util.Optional;

import com.alten.hotel.booking.domain.entity.HotelEntity;

public interface IHotelRepository {
	
	public Optional<HotelEntity> getHotel(Integer id);
	
	public void saveHotel(HotelEntity hotelEntity);

}
