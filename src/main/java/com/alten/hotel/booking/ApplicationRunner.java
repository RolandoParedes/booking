package com.alten.hotel.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.dto.HotelDto;
import com.alten.hotel.booking.domain.dto.PaymentDto;
import com.alten.hotel.booking.domain.dto.PaymentMode;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.dto.RoomTypeDto;
import com.alten.hotel.booking.domain.usecases.BookingService;
import com.alten.hotel.booking.domain.usecases.ClientService;
import com.alten.hotel.booking.domain.usecases.HotelService;
import com.alten.hotel.booking.domain.usecases.PaymentService;
import com.alten.hotel.booking.domain.usecases.RoomService;
import com.alten.hotel.booking.domain.usecases.RoomTypeService;
import com.alten.hotel.booking.domain.util.CommonUtils;

@Component
public class ApplicationRunner implements CommandLineRunner{
	
	@Autowired
	private HotelService hotelServiceImpl;
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public void run(String... args) throws Exception {
	
		//Saving hotel data
		HotelDto hotel = new HotelDto(1,"Cancun Hotel", "Av. Bonampak, Manzana 1", "cancun 123", "Mexico", "Cancun", "876452321", "hotelcancun@gmail.com", 5);
		this.hotelServiceImpl.saveHotel(hotel);

		//Saving room type data
		double priceroom = 2500;
		RoomTypeDto roomtype = new RoomTypeDto(2, "Presidential suite", priceroom, "Presidential suite # 1",4);
		this.roomTypeService.saveRoomType(roomtype);
		
		//Saving room data
		RoomDto roomDto = new RoomDto(3, "304", hotel, roomtype);
		this.roomService.saveRoom(roomDto);
		
		RoomDto roomDto1 = new RoomDto(4, "305", hotel, roomtype);
		this.roomService.saveRoom(roomDto1);
		
		//Saving client data
		ClientDto clientDto = new ClientDto(5, "33244332", "Rolando","Paredes Alzamora", "1990/04/21","Peru", "Lima", "954754324", "rolandopar01@gmail.com");
		this.clientService.saveClient(clientDto);
		
		//Saving booking data
		BookingReqDto bookingDto = new BookingReqDto();
		bookingDto.setIdbooking(6);
		bookingDto.setBookingdate(LocalDate.now());
		bookingDto.setNumadults("2");
		bookingDto.setNumchildrens("2");
		bookingDto.setBookingCode(123);
		bookingDto.setArrivalDate(LocalDate.now().plusDays(1));
		bookingDto.setDepartureDate(LocalDate.now().plusDays(3));
		bookingDto.setIdclient(clientDto);
		bookingDto.setIdhotel(hotel);
		bookingDto.setIdroom(roomDto);
		bookingDto.setBookingCode(CommonUtils.generateBookingCode());
		this.bookingService.saveBooking(bookingDto);
		
		
		//Saving payment data
		double roomservice = 340;
		double totalAmount = CommonUtils.calculateTotalBookingPayment.apply(priceroom, roomservice);
		PaymentDto payment = new PaymentDto(7,priceroom,roomservice, totalAmount,PaymentMode.CASH,LocalDateTime.now(), "5435432", clientDto, bookingDto);
		this.paymentService.savePayment(payment);
		
		
	}

}
