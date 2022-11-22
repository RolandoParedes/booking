package com.alten.hotel.booking.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BOOKING")
public class BookingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idbooking;
	
	private LocalDate bookingdate;
	
	private LocalDate arrivalDate;
	
	private LocalDate departureDate;
	
	private String numadults;
	
	private String numchildrens;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClientEntity idclient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private HotelEntity idhotel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idroom")
	private RoomEntity idroom;  
	
	private Integer bookingCode;

}
