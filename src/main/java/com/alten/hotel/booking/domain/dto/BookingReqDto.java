package com.alten.hotel.booking.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingReqDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int idbooking;
	
	private LocalDate bookingdate;
	
    @NotNull(message = "arrivalDate field must not be null")
	private LocalDate arrivalDate;
	
    @NotNull(message = "departureDate field must not be null")
	private LocalDate departureDate;
	
	@NotEmpty(message = "numadults field must not be empty")
    @NotNull(message = "numadults field must not be null")
	private String numadults;
	
	@NotEmpty(message = "numchildrens field must not be empty")
    @NotNull(message = "numchildrens field must not be null")
	private String numchildrens;
	
	@NotNull(message = "idclient field must not be null")
	private ClientDto idclient;
	
	@NotNull(message = "idhotel field must not be null")
	private HotelDto idhotel;
	
	@NotNull(message = "idroom field must not be null")
	private RoomDto idroom;  
	
	private Integer bookingCode;

}
