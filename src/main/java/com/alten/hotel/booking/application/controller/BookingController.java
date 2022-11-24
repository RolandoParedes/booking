package com.alten.hotel.booking.application.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alten.hotel.booking.domain.dto.ApiResponse;
import com.alten.hotel.booking.domain.dto.BookingReqDto;
import com.alten.hotel.booking.domain.dto.RoomDto;
import com.alten.hotel.booking.domain.usecases.BookingService;
import com.alten.hotel.booking.domain.usecases.RoomService;
import com.alten.hotel.booking.domain.util.CommonUtils;
import com.alten.hotel.booking.infraestructure.exception.MessageValidation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "Booking" })
@RestController
@RequestMapping("/api/v1/booking")
@Validated
public class BookingController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private BookingService bookingService;
	
	public BookingController(RoomService roomService,BookingService bookingService) {
    	this.roomService = roomService;
    	this.bookingService = bookingService;
    }

	@ApiOperation(
			value = "Retrieves room that are availables",
			notes = "A maximum of 100 available rooms will be returned.")
	@GetMapping(value = "/get", produces = { "application/json" })
	public ResponseEntity<?> getRoomAvailables(
			@ApiParam(value = "numadults", required = true) @RequestParam @NotEmpty(message = "numadults must not be empty") String numadults,
			@ApiParam(value = "numchildrens", required = true) @RequestParam @NotEmpty(message = "numchildrens must not be empty") String numchildrens,
			@ApiParam(value = "arrivalDate", required = true) @RequestParam @NotEmpty(message = "arrivalDate must not be empty") String arrivalDate,
			@ApiParam(value = "departureDate", required = true) @RequestParam @NotEmpty(message = "departureDate must not be empty") String departureDate,
			@ApiParam(value = "Starting index of the page we want to retrieve", allowableValues = "range[0, infinity]", defaultValue = "0") @RequestParam(required = false) Integer start,
			@ApiParam(value = "Page size", allowableValues = "range[0, 100]", defaultValue = "100") @RequestParam(required = false) Integer limit) {

		ApiResponse<List<RoomDto>> response = new ApiResponse<>();
		
		Map<String,ApiResponse> msgValidation = MessageValidation.validateBooking(new BookingReqDto(0, null, LocalDate.parse(arrivalDate), LocalDate.parse(departureDate), numadults, numchildrens, null, null, null, null));
		
		if(!msgValidation.isEmpty()) {
			response.setCode(msgValidation.get("ERROR").getCode());
			response.setMessage(msgValidation.get("ERROR").getMessage());
			return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		}
		
		List<RoomDto> lstResp = this.roomService.getAvailableRooms(Integer.parseInt(numadults),Integer.parseInt(numchildrens), LocalDate.parse(arrivalDate), LocalDate.parse(departureDate), start, CommonUtils.getLimitMaximun(limit));
		response.setResponse(lstResp);
		response.setMessage("Successfull");
		response.setCode(HttpStatus.ACCEPTED.toString());
		
		LOGGER.debug("BookingController getRoomAvailables: {}", response);
		
		return new ResponseEntity<>(response,HttpStatus.OK);

	}
	
	
	@ApiOperation(
			value = "Creates a booking",
			notes = "A maximum of 100 available rooms will be returned.")
	@PostMapping(value="/create", produces = {"application/json"})
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingReqDto bookingReqDto){
		
		ApiResponse<BookingReqDto> response = new ApiResponse<>();
		
		Map<String,ApiResponse> msgValidation = MessageValidation.validateBooking(bookingReqDto);
		
		if(!msgValidation.isEmpty()) {
			response.setCode(msgValidation.get("ERROR").getCode());
			response.setMessage(msgValidation.get("ERROR").getMessage());
			return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		}
		
		BookingReqDto bookingDto = this.bookingService.saveBooking(bookingReqDto);
		response.setResponse(bookingDto);
		response.setMessage("Successfull");
		response.setCode(HttpStatus.ACCEPTED.toString());
		
		LOGGER.debug("BookingController createBooking: {}", response);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	
	@ApiOperation(
			value = "Delete a booking",
			notes = "A maximum of 100 available rooms will be returned.")
	@DeleteMapping(value="/delete", produces = {"application/json"})
    public ResponseEntity<?> deleteBooking(@Valid @RequestBody BookingReqDto bookingReqDto){
		
		ApiResponse<BookingReqDto> response = new ApiResponse<>();
		
		this.bookingService.deleteBookingById(bookingReqDto);

		response.setMessage("Successfull");
		response.setCode(HttpStatus.ACCEPTED.toString());
		
		LOGGER.debug("BookingController deleteBooking: {}", response);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	
	@ApiOperation(
			value = "Update a booking",
			notes = "After updating the booking, the first booking gets deleted.")
	@PutMapping(value="/update", produces = {"application/json"})
    public ResponseEntity<?> updateBooking(@Valid @RequestBody BookingReqDto bookingReqDto){
		
		ApiResponse<BookingReqDto> response = new ApiResponse<>();
		
		Map<String,ApiResponse> msgValidation = MessageValidation.validateBooking(bookingReqDto);
		
		if(!msgValidation.isEmpty()) {
			response.setCode(msgValidation.get("ERROR").getCode());
			response.setMessage(msgValidation.get("ERROR").getMessage());
			return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		}
		
		BookingReqDto bsave = this.bookingService.updateBooking(bookingReqDto);
		response.setResponse(bsave);
		response.setMessage("Successfull");
		response.setCode(HttpStatus.ACCEPTED.toString());
		
		LOGGER.debug("BookingController updateBooking: {}", response);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
