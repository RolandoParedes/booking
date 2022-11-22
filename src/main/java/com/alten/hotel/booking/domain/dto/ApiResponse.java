package com.alten.hotel.booking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse <T>{
	
	public String message;
	
	public String code;
	
	public T response;
	
	

}
