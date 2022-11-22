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
public class ClientDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idclient;
	
	private String dni;
	
	private String name;
	
	private String lastnames;
	
	private String birthdate;
	
	private String country;
	
	private String city;
	
	private String phone;
	
	private String mail;
	
	

}
