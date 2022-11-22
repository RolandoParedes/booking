package com.alten.hotel.booking.application.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.alten.hotel.booking.domain.dto.ApiResponse;
import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.usecases.BookingService;
import com.alten.hotel.booking.domain.usecases.RoomService;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BookingControllerTest {
	
	BookingController controller;
	
	@Mock
	RoomService roomService;
	
	@Mock
	BookingService bookingService;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
		controller = spy(new BookingController(roomService, bookingService));
		mockMvc = standaloneSetup(controller).build();
	}
	
	@Nested
	class WhenBooking {
		
		@Mock
		ApiResponse<List<RoomDto>> response;
		
		@Mock
		List<RoomDto> rooms;
		
		@Mock
		BookingReqDto bookingDto;

		@BeforeEach
		void init() {
			MockitoAnnotations.initMocks(this);
			doReturn(rooms).when(roomService).getAvailableRooms(any(), any(), any(), any(), any(), any());
		}
		
		
		@Nested
		class WhenCallMethod {
			
			@BeforeEach
			void init() {
				MockitoAnnotations.initMocks(this);
				doReturn(bookingDto).when(bookingService).saveBooking(any());
				doReturn(bookingDto).when(bookingService).updateBooking(any());
			}
			
			@Test
			void thatGets() throws Exception {
				ResponseEntity<?> result = controller.getRoomAvailables("2", "2", "2022-11-10", "2022-11-12", 0, 10);
				assertNotNull(result);
			}
			
			@Test
			void thatPost() throws Exception {
				ResponseEntity<?> result = controller.createBooking(getBookingDto());
				assertNotNull(result);
			}
			
			@Test
			void thatPut() throws Exception {
				ResponseEntity<?> result = controller.updateBooking(getBookingDto());
				assertNotNull(result);
			}
			
			@Test
			void thatDelete() throws Exception {
				ResponseEntity<?> result = controller.deleteBooking(getBookingDto());
				assertNotNull(result);
			}
		}
		
		@Nested
		class WhenMockMvc {

			@BeforeEach
			public void init(){
				List<RoomDto> lstResp = new LinkedList<>();
				doReturn(lstResp).when(roomService).getAvailableRooms(any(), any(), any(), any(), any(), any());
			}

			@Test
			void thatGets() throws Exception {
				
				mockMvc.perform(get("/api/v1/booking/get?numadults=2&numchildrens=2&arrivalDate=2022-11-20&departureDate=2022-11-22&start=0&limit10"))
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON));

			}
			
			
			@Test
			void thatPost() throws Exception {
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				
				String content = objectMapper.writeValueAsString(getBookingDto());
				mockMvc.perform(post("/api/v1/booking/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			}
			
			
			@Test
			void thatPut() throws Exception {	

				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
				
				String content = objectMapper.writeValueAsString(getBookingDto());
				mockMvc.perform(put("/api/v1/booking/update")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			}
			
			@Test
			void thatDelete() throws Exception {
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				
				String content = objectMapper.writeValueAsString(getBookingDto());
				mockMvc.perform(delete("/api/v1/booking/delete")
						.content(content)
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON));
				
			}
			
			
		}
	}
	
	private BookingReqDto getBookingDto() {
		BookingReqDto bookingReqDto = new BookingReqDto();
		bookingReqDto.setIdbooking(22);
		bookingReqDto.setNumadults("2");
		bookingReqDto.setNumchildrens("2");
		bookingReqDto.setBookingCode(222);
		bookingReqDto.setArrivalDate(LocalDate.now().plusDays(2));
		bookingReqDto.setDepartureDate(LocalDate.now().plusDays(4));
		bookingReqDto.setBookingdate(LocalDate.now());
		bookingReqDto.setIdclient(new ClientDto(5, "33244332", "Rolando","Paredes Alzamora", "1990/04/21","Peru", "Lima", "954754324", "rolandopar01@gmail.com"));
		bookingReqDto.setIdhotel(new HotelDto(1,"Cancun Hotel", "Av. Bonampak, Manzana 1", "cancun 123", "Mexico", "Cancun", "876452321", "hotelcancun@gmail.com", 5));
		bookingReqDto.setIdroom(new RoomDto(1, "3333", null, null));
		return bookingReqDto;
	}

}
