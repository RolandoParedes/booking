package com.alten.hotel.booking.domain.port.service;

import com.alten.hotel.booking.domain.dto.PaymentDto;

public interface IPaymentService {
	
	public void savePayment(PaymentDto dto);

}
