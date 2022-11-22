package com.alten.hotel.booking.domain.port.repository;

import com.alten.hotel.booking.domain.entity.RoomTypeEntity;

public interface IRoomTypeRepository {
	
	public void saveRoomType(RoomTypeEntity typeEntity);

}
