package com.alten.hotel.booking.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.domain.entity.HotelEntity;
import com.alten.hotel.booking.domain.port.service.IHotelService;
import com.alten.hotel.booking.domain.util.EntityDtoUtil;
import com.alten.hotel.booking.infraestructure.adapter.repository.HotelRepository;

@Service
public class HotelService implements IHotelService{
	
	@Autowired
	private HotelRepository repository;
	
	public HotelService(HotelRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveHotel(HotelDto hotelDto) {
		HotelEntity entity = EntityDtoUtil.convertHotelDtotoEntity(hotelDto);
		this.repository.saveHotel(entity);
	}

}
