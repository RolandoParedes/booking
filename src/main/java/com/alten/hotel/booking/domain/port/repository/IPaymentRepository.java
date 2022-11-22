package com.alten.hotel.booking.domain.port.repository;

import com.alten.hotel.booking.domain.entity.PaymentEntity;

public interface IPaymentRepository {
	
	public void savePayment(PaymentEntity paymententity);

}
