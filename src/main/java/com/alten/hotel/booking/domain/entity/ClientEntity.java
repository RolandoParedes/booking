package com.alten.hotel.booking.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CLIENT")
public class ClientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
