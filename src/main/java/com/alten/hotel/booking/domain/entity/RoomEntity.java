package com.alten.hotel.booking.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "ROOM")
public class RoomEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idroom;
	
	private String roomnumber;
	
	@OneToOne
    @JoinColumn(name = "idhotel", referencedColumnName = "id")
	private HotelEntity idhotel;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idroomtype", referencedColumnName = "idroomtype")
	private RoomTypeEntity idroomtype;

}
