package com.alten.hotel.booking.infraestructure.adapter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.entity.RoomTypeEntity;
import com.alten.hotel.booking.domain.port.repository.IRoomTypeRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.jpa.RoomTypeRepositoryJPA;

@Component
public class RoomTypeRepository implements IRoomTypeRepository{
	
	@Autowired
	private RoomTypeRepositoryJPA repository;

	@Override
	public void saveRoomType(RoomTypeEntity typeEntity) {
		this.repository.save(typeEntity);
		
	}

}
