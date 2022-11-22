package com.alten.hotel.booking.infraestructure.adapter.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.entity.HotelEntity;
import com.alten.hotel.booking.domain.port.repository.IHotelRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.jpa.HotelRepositoryJPA;

@Component
public class HotelRepository implements IHotelRepository{
	
	@Autowired
	private HotelRepositoryJPA repository;

	@Override
	public Optional<HotelEntity> getHotel(Integer id) {
		return this.repository.findById(id);
	}

	@Override
	public void saveHotel(HotelEntity hotelEntity) {
		this.repository.save(hotelEntity);
		
	}

}
