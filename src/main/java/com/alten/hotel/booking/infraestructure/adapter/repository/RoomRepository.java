package com.alten.hotel.booking.infraestructure.adapter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.entity.RoomEntity;
import com.alten.hotel.booking.domain.port.repository.IRoomRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.jpa.RoomRepositoryJPA;

@Component
public class RoomRepository implements IRoomRepository{
	
	@Autowired
	private RoomRepositoryJPA repository;

	@Override
	public void save(RoomEntity roomEntity) {
		this.repository.saveAndFlush(roomEntity);
	}

	@Override
	public List<RoomEntity> getAvailableRooms(Integer maxpersons, Integer start, Integer limit) {
		Pageable pageable = PageRequest.of(start, limit);
		return this.repository.getAvailableRooms(maxpersons,pageable);
	}

	@Override
	public Optional<RoomEntity> findRoomById(int idroom) {
		return this.repository.findById(idroom);
	}

}
