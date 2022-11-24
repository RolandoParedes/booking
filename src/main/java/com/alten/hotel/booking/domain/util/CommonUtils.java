package com.alten.hotel.booking.domain.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.alten.hotel.booking.domain.dto.BookingReqDto;

public class CommonUtils {
	
	private CommonUtils() {}
	
	/*
	 * Method that evaluates that a booking can't be more than 30 days in advance
	 * @Input BookingReqDto (arrivalDate)
	 * @Output boolean response
	 * */
	public static Predicate<BookingReqDto> evaluateMaxRangeDaysBooked = b -> {
		LocalDate datetimenow = LocalDate.now();
		long rangeDays = ChronoUnit.DAYS.between(datetimenow, b.getArrivalDate()); 
		return rangeDays > 30;
	};
	
	
	/*
	 * Method that evaluates that a booking can't be more than 3 days
	 * @Input BookingReqDto (arrivalDate,departureDate)
	 * @Output boolean response
	 * */
	public static Predicate<BookingReqDto> evaluateMaxDaysBooked = b -> { 
		long maxDays = ChronoUnit.DAYS.between(b.getArrivalDate(), b.getDepartureDate()); 
		return maxDays > 3;
	};
	
	/*
	 * Method that evaluates if arrival date is after departure date
	 * @Input BookingReqDto (arrivalDate,departureDate)
	 * @Output boolean response
	 * */
	public static Predicate<BookingReqDto> evaluateArrivalDateDepartureDate = b -> { 
		return b.getArrivalDate().isAfter(b.getDepartureDate());
	};
 

	/*
	 * Method that evaluates that the reservation can not start the same day he has booked
	 * @Input BookingReqDto (arrivalDate)
	 * @Output boolean response
	 * */
	public static Predicate<BookingReqDto> evaluatebookingDateDifferArrivalDate = b -> { 
		LocalDate datetimenow = LocalDate.now();		
		return datetimenow.equals(b.getArrivalDate());
		
	};
	
	public static Predicate<BookingReqDto> evaluateDayAfterToday = b -> {
		LocalDate datetimenow = LocalDate.now();
		return datetimenow.isAfter(b.getArrivalDate());
	};
	
	
	/*
	 * Method that generates a random number for booking code
	 * @Output random number
	 * */
	public static Integer generateBookingCode() {
		Random random = new Random();
		return random.nextInt(10000,80000);
	}
	

	/*
	 * Method that calculates the total booking payment
	 * @Input roomservice amount
	 * @Input roomcharge (room cost)
	 * @Output total amount to pay
	 * */
	public static BiFunction<Double,Double,Double> calculateTotalBookingPayment = (rms,rmc) ->  rms + rmc; 
	
	
	/*
	 * Method that calculates the quantity of persons
	 * @Input numadults int
	 * @Input numchilds int
	 * @Output total persons
	 * */
	public static BiFunction<Integer,Integer,Integer> calculateMaxPersons = (ad,ch) ->  ad + ch;
	
	/*
	 * Method that retrieves the maximum of limit number
	 * @Input limit Integer
	 * @Output limitMaximum Integer
	 * */
	public static Integer getLimitMaximun(Integer limit) {
		
		if(limit == null || limit > 100) {
			return 100;
		}
		return limit;
	}
	
	
	public static Predicate<BookingReqDto> validateNotsameDate = d -> d.getArrivalDate().equals(d.getDepartureDate());

}
