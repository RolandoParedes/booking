package com.alten.hotel.booking.infraestructure.adapter.repository.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alten.hotel.booking.domain.entity.BookingEntity;

@Repository
public interface BookingRepositoryJPA extends JpaRepository<BookingEntity, Integer>{
	
	@Query(value = "SELECT b.* FROM BOOKING b "
				  + "LEFT JOIN ROOM r "
			      + "ON b.IDROOM = r.IDROOM "
			      + "LEFT JOIN ROOMTYPE t "
			      + "ON r.IDROOMTYPE = t.IDROOMTYPE "
				  + "WHERE t.maxpersons = :maxpersons "
				  + "AND b.ARRIVAL_DATE NOT BETWEEN :arrivaldate AND :departureDate "
				  + "AND b.DEPARTURE_DATE NOT BETWEEN :arrivaldate AND :departureDate"
				  ,nativeQuery = true)
	public List<BookingEntity> getAvailableRooms(int maxpersons,
												 LocalDate departureDate,
												 LocalDate arrivaldate,
												 Pageable pageable);
	
	
	
	@Query("SELECT b FROM BookingEntity b "
		  + "WHERE b.bookingCode = :bookingCode "
		  + "AND b.idclient.idclient = :clientId")
	public BookingEntity findBookingByCodeAndClientId(int bookingCode, int clientId);
	
	
	
	@Query(value = "SELECT b.* FROM BOOKING b "
			  + "LEFT JOIN ROOM r "
		      + "ON b.IDROOM = r.IDROOM "
		      + "LEFT JOIN ROOMTYPE t "
		      + "ON r.IDROOMTYPE = t.IDROOMTYPE "
			  + "WHERE r.idroom = :idRoom "
			  + "AND b.ARRIVAL_DATE NOT BETWEEN :arrivaldate AND :departureDate "
			  + "AND b.DEPARTURE_DATE NOT BETWEEN :arrivaldate AND :departureDate"
			  ,nativeQuery = true)
	public BookingEntity findAvailableRoomAnotherDate(int idRoom,
													  LocalDate arrivaldate,
													  LocalDate departureDate);


}
