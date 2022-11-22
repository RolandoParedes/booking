package com.alten.hotel.booking.domain.usecases;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alten.hotel.booking.domain.dto.RoomTypeDto;
import com.alten.hotel.booking.infraestructure.adapter.repository.RoomTypeRepository;

public class RoomTypeServiceTest {
	
	@Nested
	class whenSaveRoomType{
		
		RoomTypeService service;
		
		@Mock
		RoomTypeRepository repository;
		
		@Mock
		RoomTypeDto dto;
	
		@BeforeEach
		void init() throws Exception {
			MockitoAnnotations.initMocks(this);
			service = spy(new RoomTypeService(repository));
		}
		
		
		@Test
		void saveRoomType() {
			service.saveRoomType(dto);
		}
	
	}

}
