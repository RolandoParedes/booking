package com.alten.hotel.booking.infraestructure.adapter.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alten.hotel.booking.domain.entity.RoomEntity;

@Repository
public interface RoomRepositoryJPA extends JpaRepository<RoomEntity, Integer>{
	
	@Query(value = "SELECT r.* FROM ROOM r "
				+ "LEFT JOIN ROOMTYPE t "
				+ "ON r.idroomtype=t.idroomtype "
				+ "WHERE t.maxpersons = :maxpersons "
				+ "AND r.IDROOM NOT IN (SELECT IDROOM FROM BOOKING)",nativeQuery = true)
	public List<RoomEntity> getAvailableRooms(Integer maxpersons,Pageable pageable);

}
