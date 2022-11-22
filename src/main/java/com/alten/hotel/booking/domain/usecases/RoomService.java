package com.alten.hotel.booking.domain.usecases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.entity.BookingEntity;
import com.alten.hotel.booking.domain.entity.RoomEntity;
import com.alten.hotel.booking.domain.port.service.IRoomservice;
import com.alten.hotel.booking.domain.util.CommonUtils;
import com.alten.hotel.booking.domain.util.EntityDtoUtil;
import com.alten.hotel.booking.infraestructure.adapter.repository.BookingRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.RoomRepository;

@Service
public class RoomService implements IRoomservice{
	
	@Autowired
	private RoomRepository roomrepository;
	
	@Autowired
	private BookingRepository bookingrepository;
	
	public RoomService(RoomRepository roomrepository, BookingRepository bookingrepository) {
		this.roomrepository = roomrepository;
		this.bookingrepository = bookingrepository;
	}

	@Override
	public void saveRoom(RoomDto dto) {
		RoomEntity entity = EntityDtoUtil.convertRoomDtoToEntity(dto);
		this.roomrepository.save(entity);
	}

	@Override
	public List<RoomDto> getAvailableRooms(Integer numadults, Integer numchildrens, LocalDate arrivalDate, LocalDate departureDate, Integer start, Integer limit) {
		
		int maxPersons = CommonUtils.calculateMaxPersons.apply(numadults, numchildrens);
		
		List<RoomEntity> listRoomsEntity = this.roomrepository.getAvailableRooms(maxPersons, start, limit);
		
		List<RoomDto> roomsDto = new ArrayList<>();
		
		if(!listRoomsEntity.isEmpty()) {
			roomsDto = listRoomsEntity.stream().map(EntityDtoUtil::convertRoomEntityToDto).collect(Collectors.toList());
		}
		
		List<BookingEntity> lstBookings = this.bookingrepository.getBookingAvaibility(maxPersons, arrivalDate, departureDate, start, limit);
		
		if(!lstBookings.isEmpty()) {
			List<BookingReqDto> booksDto = lstBookings.stream().map(EntityDtoUtil::convertBookingEntityToDto).collect(Collectors.toList());
		
			for(BookingReqDto dto :booksDto) {
				roomsDto.add(dto.getIdroom());
			}
		
		}
		
		return roomsDto;
		
	}

	@Override
	public RoomDto findRoomById(int idroom) {
		Optional<RoomEntity> entity = this.roomrepository.findRoomById(idroom);
		return !entity.isEmpty() ? EntityDtoUtil.convertRoomEntityToDto(entity.get()) : null;
	}

}
