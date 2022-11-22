package com.alten.hotel.booking.domain.port.service;

import java.time.LocalDate;
import java.util.List;

import com.alten.hotel.booking.domain.dto.RoomDto;

public interface IRoomservice {
	
	public void saveRoom(RoomDto dto);
	
	public List<RoomDto> getAvailableRooms(Integer numadults, Integer numchildrens, LocalDate arrivalDate, LocalDate departureDate, Integer start, Integer limit);

	public RoomDto findRoomById(int idroom);
	
}
