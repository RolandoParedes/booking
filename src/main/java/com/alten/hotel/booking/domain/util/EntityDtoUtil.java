package com.alten.hotel.booking.domain.util;

import org.springframework.beans.BeanUtils;

import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.domain.dto.PaymentDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.dto.RoomTypeDto;
import com.alten.hotel.booking.domain.entity.BookingEntity;
import com.alten.hotel.booking.domain.entity.ClientEntity;
import com.alten.hotel.booking.domain.entity.HotelEntity;
import com.alten.hotel.booking.domain.entity.PaymentEntity;
import com.alten.hotel.booking.domain.entity.RoomEntity;
import com.alten.hotel.booking.domain.entity.RoomTypeEntity;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;

public class EntityDtoUtil {
	
	private EntityDtoUtil() {}
	
	public static HotelEntity convertHotelDtotoEntity(HotelDto dto) {
		
		HotelEntity entity = new HotelEntity();
		BeanUtils.copyProperties(dto, entity);
		
		return entity;
	}
	
	public static RoomTypeEntity convertRoomTypeDtoToEntity(RoomTypeDto dto) {
		
		RoomTypeEntity entity = new RoomTypeEntity();
		BeanUtils.copyProperties(dto, entity);
		
		return entity;
	}
	
	public static RoomEntity convertRoomDtoToEntity(RoomDto dto) {
		
		RoomEntity roomEntity = new RoomEntity();
		
		ModelMapper  mapper = new ModelMapper();
		
		HotelEntity hotelEntity = mapper.map(dto.getIdhotel(), HotelEntity.class);
		roomEntity.setIdhotel(hotelEntity);
		
		RoomTypeEntity roomTypeEntity = mapper.map(dto.getIdroomtype(), RoomTypeEntity.class);
		roomEntity.setIdroomtype(roomTypeEntity);
		
		BeanUtils.copyProperties(dto, roomEntity);
		
		return roomEntity;
	}
	
	public static RoomDto convertRoomEntityToDto(RoomEntity entity) {
		
		RoomDto roomDto = new RoomDto();
		
		ModelMapper  mapper = new ModelMapper();
		
		HotelDto hotelDto = mapper.map(entity.getIdhotel(), HotelDto.class);
		roomDto.setIdhotel(hotelDto);
		
		RoomTypeDto roomtypeDto = mapper.map(entity.getIdroomtype(), RoomTypeDto.class);
		roomDto.setIdroomtype(roomtypeDto);
		
		BeanUtils.copyProperties(entity, roomDto);
		
		return roomDto;
	}
	
	public static ClientEntity convertClientDtoToEntity(ClientDto dto) {
		
		ClientEntity entity = new ClientEntity();
		BeanUtils.copyProperties(dto, entity);
		
		return entity;
	}
	
	public static BookingEntity convertBookingDtoToEntity(BookingReqDto dto) {
		
		BookingEntity bookingEntity = new BookingEntity();
		
		ModelMapper  mapper = new ModelMapper();
		
		ClientEntity clientEntity = mapper.map(dto.getIdclient(), ClientEntity.class);
		bookingEntity.setIdclient(clientEntity);
		
		HotelEntity hotelEntity = mapper.map(dto.getIdhotel(), HotelEntity.class);
		bookingEntity.setIdhotel(hotelEntity);
		
		RoomEntity roomEntity = mapper.map(dto.getIdroom(), RoomEntity.class);
		bookingEntity.setIdroom(roomEntity);
		
		BeanUtils.copyProperties(dto, bookingEntity);
		
		return bookingEntity;
	}
	
	public static BookingReqDto convertBookingEntityToDto(BookingEntity entity) {
		
		BookingReqDto bookingDto = new BookingReqDto();
		
		ModelMapper  mapper = new ModelMapper();
		
		ClientDto clientDto = mapper.map(entity.getIdclient(), ClientDto.class); 
		bookingDto.setIdclient(clientDto);
		
		HotelDto hotelDto = mapper.map(entity.getIdhotel(), HotelDto.class);
		bookingDto.setIdhotel(hotelDto);
		
		RoomDto roomDto = mapper.map(entity.getIdroom(), RoomDto.class);
		bookingDto.setIdroom(roomDto);
		
		BeanUtils.copyProperties(entity, bookingDto);
		
		return bookingDto;
	}
	
	public static PaymentEntity convertPaymentDtoToEntity(PaymentDto dto) {
		
		PaymentEntity paymentEntity = new PaymentEntity();
		
		ModelMapper  mapper = new ModelMapper();
		
		ClientEntity clientEntity = mapper.map(dto.getIdclient(), ClientEntity.class);
		paymentEntity.setIdclient(clientEntity);
		
		BookingEntity bookingEntity = mapper.map(dto.getIdbooking(), BookingEntity.class);
		paymentEntity.setIdbooking(bookingEntity);
		
		BeanUtils.copyProperties(dto, paymentEntity);
		
		return paymentEntity;
		
	}

}
