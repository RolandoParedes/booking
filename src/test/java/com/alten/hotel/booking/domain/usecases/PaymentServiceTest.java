package com.alten.hotel.booking.domain.usecases;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.dto.PaymentDto;
import com.alten.hotel.booking.infraestructure.adapter.repository.PaymentRepository;

public class PaymentServiceTest {
	
	@Nested
	class whenSavePayment{
		
		PaymentService service;
		
		@Mock
		PaymentRepository repository;
		
		PaymentDto dto = new PaymentDto();
	
		@BeforeEach
		void init() throws Exception {
			MockitoAnnotations.initMocks(this);
			service = spy(new PaymentService(repository));
		}
		
		
		@Test
		void savePayment() {
			dto.setIdclient(new ClientDto(1, "33244332", "Rolando","Paredes Alzamora", "1990/04/21","Peru", "Lima", "954754324", "rolandopar01@gmail.com"));
			BookingReqDto booking = new BookingReqDto();
			booking.setIdbooking(2);
			dto.setIdbooking(booking);
			service.savePayment(dto);
		}
	
	}

}
