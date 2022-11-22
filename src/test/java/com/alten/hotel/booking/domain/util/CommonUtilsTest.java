package com.alten.hotel.booking.domain.util;

import java.time.LocalDate;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.alten.hotel.booking.domain.dto.BookingReqDto;

public class CommonUtilsTest {
	
	@Nested
	class whenCommonsUtil{
		
		CommonUtils commonUtils;
		
		@Test
		void evaluateMaxRangeDaysBooked() {
			
			BookingReqDto booking = new BookingReqDto();
			booking.setArrivalDate(LocalDate.now().plusDays(45));
			
			boolean resp = CommonUtils.evaluateMaxRangeDaysBooked.test(booking);
			assertEquals(true,resp);
			
		}
		
		@Test
		void evaluateMaxDaysBooked() {
			
			BookingReqDto booking = new BookingReqDto();
			booking.setArrivalDate(LocalDate.now());
			booking.setDepartureDate(LocalDate.now().plusDays(4));
			
			boolean resp = CommonUtils.evaluateMaxDaysBooked.test(booking);
			assertEquals(true,resp);
			
		}
		
		@Test
		void evaluatebookingDateDifferArrivalDate() {
			
			BookingReqDto booking = new BookingReqDto();
			booking.setArrivalDate(LocalDate.now());
			
			boolean resp = CommonUtils.evaluatebookingDateDifferArrivalDate.test(booking);
			assertEquals(true,resp);
			
		}
		
		@Test
		void validateNotsameDate() {
			
			BookingReqDto booking = new BookingReqDto();
			booking.setArrivalDate(LocalDate.now());
			booking.setDepartureDate(LocalDate.now());
			
			boolean resp = CommonUtils.validateNotsameDate.test(booking);
			assertEquals(true,resp);
			
		}
		
		@Test
		void generateBookingCode() {
			
			Integer code = CommonUtils.generateBookingCode();
			assertNotNull(code);
			
		}
		
		@Test
		void getLimitMaximun() {
			
			Integer value = CommonUtils.getLimitMaximun(8000);
			assertEquals(100,value);
			
		}
		
		@Test
		void calculateTotalBookingPayment() {
			
			Double value = CommonUtils.calculateTotalBookingPayment.apply(10D,10D);
			assertEquals(20,value);
			
		}
		
		@Test
		void calculateMaxPersons() {
			
			Integer value = CommonUtils.calculateMaxPersons.apply(2,3);
			assertEquals(5,value);
			
		}
		
		
	}

}
