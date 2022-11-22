package com.alten.hotel.booking.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idroom;
	
	private String roomnumber;
	
	@JsonIgnore
	private HotelDto idhotel;
	
	private RoomTypeDto idroomtype;

}
