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
public class RoomTypeDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int idroomtype;
	
	private String name;
	
	private double price;
	
	private String descripcion;
	
	private int maxpersons;
	

}
