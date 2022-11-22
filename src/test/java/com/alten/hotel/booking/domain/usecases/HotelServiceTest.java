package com.alten.hotel.booking.domain.usecases;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.infraestructure.adapter.repository.HotelRepository;

public class HotelServiceTest {
	
	@Nested
	class whenSaveHotel{
		
		HotelService service;
		
		@Mock
		HotelRepository repository;
		
		@Mock
		HotelDto dto;
	
		@BeforeEach
		void init() throws Exception {
			MockitoAnnotations.initMocks(this);
			service = spy(new HotelService(repository));
		}
		
		
		@Test
		void saveHotel() {
			service.saveHotel(dto);
		}
	
	}

}
