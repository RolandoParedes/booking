package com.alten.hotel.booking.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.hotel.booking.domain.dto.RoomTypeDto;
import com.alten.hotel.booking.domain.entity.RoomTypeEntity;
import com.alten.hotel.booking.domain.port.service.IRoomTypeService;
import com.alten.hotel.booking.domain.util.EntityDtoUtil;
import com.alten.hotel.booking.infraestructure.adapter.repository.RoomTypeRepository;

@Service
public class RoomTypeService implements IRoomTypeService{
	
	@Autowired
	private RoomTypeRepository repository;
	
	public RoomTypeService(RoomTypeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveRoomType(RoomTypeDto typeDto) {
		
		RoomTypeEntity entity = EntityDtoUtil.convertRoomTypeDtoToEntity(typeDto);
		this.repository.saveRoomType(entity);
	}

}
