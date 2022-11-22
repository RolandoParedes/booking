package com.alten.hotel.booking.infraestructure.adapter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.entity.PaymentEntity;
import com.alten.hotel.booking.domain.port.repository.IPaymentRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.jpa.PaymentRepositoryJPA;

@Component
public class PaymentRepository implements IPaymentRepository{
	
	@Autowired
	private PaymentRepositoryJPA repository;

	@Override
	public void savePayment(PaymentEntity paymententity) {
		this.repository.save(paymententity);
	}

}
