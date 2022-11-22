package com.alten.hotel.booking.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idpayment;
	
	private double roomcharge;
	
	private double roomservice;
	
	private double totalpayment;
	
	private PaymentMode paymentmode;
	
	private LocalDateTime paymendate;
	
	private String cardnumber;
	
	private ClientDto idclient;
	
	private BookingReqDto idbooking;
	
 

}
