package com.alten.hotel.booking.domain.usecases;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.entity.BookingEntity;
import com.alten.hotel.booking.domain.entity.ClientEntity;
import com.alten.hotel.booking.domain.port.service.IBookingService;
import com.alten.hotel.booking.domain.util.CommonUtils;
import com.alten.hotel.booking.domain.util.EntityDtoUtil;
import com.alten.hotel.booking.infraestructure.adapter.repository.BookingRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.ClientRepository;

@Service
public class BookingService implements IBookingService{
	
	@Autowired
	private BookingRepository bookinRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RoomService roomService;
	
	public BookingService(BookingRepository bookinRepository, ClientRepository clientRepository,RoomService roomService) {
		this.bookinRepository = bookinRepository;
		this.clientRepository = clientRepository;
		this.roomService = roomService;
	}

	@Transactional
	@Override
	public BookingReqDto saveBooking(BookingReqDto bookingReqDto) {
		
		RoomDto room = this.roomService.findRoomById(bookingReqDto.getIdroom().getIdroom());
		
		if(room == null) {
			throw new RuntimeException("Room specified is not correct");
		}
		
		List<RoomDto> lstResp = this.roomService.getAvailableRooms(Integer.parseInt(bookingReqDto.getNumadults()),Integer.parseInt(bookingReqDto.getNumchildrens()), 
				bookingReqDto.getArrivalDate(), bookingReqDto.getDepartureDate(), 0, 10);

		if(lstResp.isEmpty()) {
			throw new RuntimeException("No room available");
		}
		
		boolean isSameRoomNumber = false;
		
		for(RoomDto rm : lstResp) {
			if(rm.getIdroom() == bookingReqDto.getIdroom().getIdroom()) {
				isSameRoomNumber = true;
			}
		}
		
		if(!isSameRoomNumber) {
			throw new RuntimeException("Room send was not the same you looked for");
		}
		
		ClientEntity clientEntity = this.clientRepository.saveClient(EntityDtoUtil.convertClientDtoToEntity(bookingReqDto.getIdclient()));
		bookingReqDto.setIdclient(new ClientDto(clientEntity.getIdclient(), clientEntity.getDni(), clientEntity.getName(),clientEntity.getLastnames(), clientEntity.getBirthdate(), clientEntity.getCountry(), clientEntity.getCity(), clientEntity.getPhone(), clientEntity.getMail()));
		bookingReqDto.setBookingdate(LocalDate.now());
		bookingReqDto.setBookingCode(CommonUtils.generateBookingCode());
	
		BookingEntity bookingEntity = this.bookinRepository.saveBooking(EntityDtoUtil.convertBookingDtoToEntity(bookingReqDto));
		return EntityDtoUtil.convertBookingEntityToDto(bookingEntity);
	}

	@Override
	public BookingReqDto findBookingByCodeAndClientId(BookingReqDto bookingReqDto) {
		BookingEntity bookingEntity = this.bookinRepository.findBookingByCodeAndClientId(bookingReqDto.getBookingCode(), bookingReqDto.getIdclient().getIdclient());
		return bookingEntity != null ? EntityDtoUtil.convertBookingEntityToDto(bookingEntity) : null;
		
	}

	@Override
	public void deleteBookingById(BookingReqDto bookingReqDto) {
		
		BookingReqDto bookingDto = this.findBookingByCodeAndClientId(bookingReqDto);
		
		if(bookingDto == null) {
			throw new RuntimeException("Booking code/Id client specified is not correct");
		}
		
		this.bookinRepository.deleteBookingCodeById(bookingDto.getIdbooking());
	}

	@Override
	public BookingReqDto findAvailableRoomAnotherDate(int idRoom, LocalDate arrivaldate, LocalDate departureDate) {
		BookingEntity bookingEntity = this.bookinRepository.findAvailableRoomAnotherDate(idRoom, arrivaldate, departureDate);
		return bookingEntity != null ? EntityDtoUtil.convertBookingEntityToDto(bookingEntity) : null;
	}

	@Override
	public BookingReqDto updateBooking(BookingReqDto bookingReqDto) {
		
		BookingReqDto bookingDto = this.findBookingByCodeAndClientId(bookingReqDto);
		
		if(bookingDto == null) {
			throw new RuntimeException("Booking code/Id client specified is not correct");
		}
		
		BookingReqDto booking = this.findAvailableRoomAnotherDate(bookingDto.getIdroom().getIdroom(), bookingDto.getArrivalDate(), bookingDto.getDepartureDate());
		
		if(booking != null) {
			throw new RuntimeException("There is no possible option to update during those dates");
		}
		
		this.deleteBookingById(bookingDto);
		
		return this.saveBooking(bookingReqDto);
	}

}
