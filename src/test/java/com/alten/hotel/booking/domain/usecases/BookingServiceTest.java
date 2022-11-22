package com.alten.hotel.booking.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.entity.BookingEntity;
import com.alten.hotel.booking.domain.entity.ClientEntity;
import com.alten.hotel.booking.domain.entity.HotelEntity;
import com.alten.hotel.booking.domain.entity.RoomEntity;
import com.alten.hotel.booking.infraestructure.adapter.repository.BookingRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.ClientRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.RoomRepository;

public class BookingServiceTest {
	
	BookingService service;
	
	@Mock
	BookingRepository bookinRepository;
	
	@Mock
	ClientRepository clientRepository;
	
	@Mock
	RoomRepository roomrepository;
	
	@Mock
	RoomService roomService;
	
	ClientEntity clientEntity;
	
	BookingEntity bookingEntity;
	
	@Mock
	RoomDto room;
	
	@BeforeEach
	void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		roomService = spy(new RoomService(roomrepository, bookinRepository));
		service = spy(new BookingService(bookinRepository, clientRepository, roomService));
	}
	
	@Nested
	class whenSaveBooking{
		
		@Mock
		List<RoomDto> lstRooms = new LinkedList<>();
		
		BookingReqDto bookingReqDto = new BookingReqDto();
		
		@Test
		void saveBooking() {
			
			doReturn(room).when(roomService).findRoomById(anyInt());
			doReturn(lstRooms).when(roomService).getAvailableRooms(any(), any(), any(), any(), any(), any());		
			
			bookingReqDto = getBookingDto();
			lstRooms.add(new RoomDto(1, "3333", null, null));
	
			bookingEntity = getBookingEntity();
			
			doReturn(bookingEntity).when(bookinRepository).saveBooking(any());
			
			BookingReqDto bkn = service.saveBooking(bookingReqDto);
			
			assertNotNull(bkn);
		}
		
	}
	
	@Nested
	class whenFindBookingByCodeAndClientId{
		
		BookingReqDto bookingReqDto = new BookingReqDto();
		
		@Test
		void findBookingByCodeAndClientId() {
					
			bookingEntity = getBookingEntity();
			
			doReturn(bookingEntity).when(bookinRepository).findBookingByCodeAndClientId(anyInt(), anyInt());

			bookingReqDto = getBookingDto();
			
			BookingReqDto bkn = service.findBookingByCodeAndClientId(bookingReqDto);
			assertNotNull(bkn);
			
		}
		
		
	}
	
	private BookingReqDto getBookingDto() {
		BookingReqDto bookingReqDto = new BookingReqDto();
		bookingReqDto.setIdroom(new RoomDto(1, "3333", null, null));
		bookingReqDto.setNumadults("2");
		bookingReqDto.setNumchildrens("2");
		bookingReqDto.setBookingCode(222);
		bookingReqDto.setArrivalDate(LocalDate.now());
		bookingReqDto.setDepartureDate(LocalDate.now());
		bookingReqDto.setIdclient(new ClientDto(5, "33244332", "Rolando","Paredes Alzamora", "1990/04/21","Peru", "Lima", "954754324", "rolandopar01@gmail.com"));
		bookingReqDto.setIdhotel(new HotelDto(1,"Cancun Hotel", "Av. Bonampak, Manzana 1", "cancun 123", "Mexico", "Cancun", "876452321", "hotelcancun@gmail.com", 5));
		bookingReqDto.setIdroom(new RoomDto(1, "3333", null, null));
		return bookingReqDto;
	}
	
	private BookingEntity getBookingEntity() {
		
		clientEntity = new ClientEntity(5, "33244332", "Rolando","Paredes Alzamora", "1990/04/21","Peru", "Lima", "954754324", "rolandopar01@gmail.com");
		doReturn(clientEntity).when(clientRepository).saveClient(any());
		
		HotelEntity hotelEnt = new HotelEntity(1,"Cancun Hotel", "Av. Bonampak, Manzana 1", "cancun 123", "Mexico", "Cancun", "876452321", "hotelcancun@gmail.com", 5);
		RoomEntity roomEnt = new RoomEntity(1, "3333", null, null);
		bookingEntity = new BookingEntity(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), "2", "2", clientEntity, hotelEnt, roomEnt, 33);	
		
		return bookingEntity;
	}

}
