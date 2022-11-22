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
@Table(name = "ROOMTYPE")
public class RoomTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idroomtype;
	
	private String name;
	
	private double price;
	
	private String descripcion;
	
	private int maxpersons;
	
	

}
