package com.alten.hotel.booking.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idHotel;
	private String name;
	private String address;
	private String postcode;
	private String country;
	private String city;
	private String phone;
	private String mail;
	private int stars;
	
	

}
