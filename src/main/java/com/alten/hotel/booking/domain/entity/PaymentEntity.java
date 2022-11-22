package com.alten.hotel.booking.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alten.hotel.booking.domain.dto.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PAYMENT")
public class PaymentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idpayment;
	
	private double roomcharge;
	
	private double roomservice;
	
	private double totalpayment;
	
	private PaymentMode paymentmode;
	
	private LocalDateTime paymendate;
	
	private String cardnumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClientEntity idclient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idbooking")
	private BookingEntity idbooking;

}
