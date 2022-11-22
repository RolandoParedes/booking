package com.alten.hotel.booking.domain.usecases;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.dto.RoomTypeDto;
import com.alten.hotel.booking.domain.entity.BookingEntity;
import com.alten.hotel.booking.domain.entity.RoomEntity;
import com.alten.hotel.booking.infraestructure.adapter.repository.BookingRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.RoomRepository;

public class RoomServiceTest {
	
	RoomService service;
	
	@Mock
	RoomRepository repository;
	
	@Mock
	BookingRepository bookingrepository;
	
	@BeforeEach
	void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = spy(new RoomService(repository, bookingrepository));
	}
	
	@Nested
	class whenSaveRoom{
		
		RoomDto dto = new RoomDto();
	
		@BeforeEach
		void init() throws Exception {
			MockitoAnnotations.initMocks(this);
		}
		
		
		@Test
		void saveRoom() {
			dto.setIdhotel(new HotelDto(1,"Cancun Hotel", "Av. Bonampak, Manzana 1", "cancun 123", "Mexico", "Cancun", "876452321", "hotelcancun@gmail.com", 5));
			dto.setIdroomtype(new RoomTypeDto(2, "Presidential suite", 2500, "Presidential suite # 1",4));
			service.saveRoom(dto);
		}
	
	}
	
	@Nested
	class whenGetAvailableRooms{
		
		@Mock
		List<RoomEntity> listRoomsEntity;
		
		@Mock
		List<BookingEntity> lstBookings;
		
		@Mock
		List<RoomDto> rooms;
		
		
		@BeforeEach
		void init() throws Exception {
			MockitoAnnotations.initMocks(this);
		}
		
		
		@Test
		void getAvailableRooms() {
			doReturn(listRoomsEntity).when(repository).getAvailableRooms(any(), any(), any());
			doReturn(lstBookings).when(bookingrepository).getBookingAvaibility(anyInt(), any(), any(), any(), any());
			
			List<RoomDto> result = service.getAvailableRooms(2, 2, LocalDate.now(), LocalDate.now(), 0, 10);
			
			assertEquals(new LinkedList<>(), result);
			
		}
		
		
	}

}
