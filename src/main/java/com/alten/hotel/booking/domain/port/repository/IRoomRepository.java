package com.alten.hotel.booking.domain.port.repository;

import java.util.List;
import java.util.Optional;

import com.alten.hotel.booking.domain.entity.RoomEntity;

public interface IRoomRepository {
	
	public void save (RoomEntity roomEntity);
	
	public List<RoomEntity> getAvailableRooms(Integer maxpersons, Integer start, Integer limit);
	
	public Optional<RoomEntity> findRoomById(int idroom);

}
