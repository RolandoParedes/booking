package com.alten.hotel.booking.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.hotel.booking.domain.dto.PaymentDto;
import com.alten.hotel.booking.domain.entity.PaymentEntity;
import com.alten.hotel.booking.domain.port.service.IPaymentService;
import com.alten.hotel.booking.domain.util.EntityDtoUtil;
import com.alten.hotel.booking.infraestructure.adapter.repository.PaymentRepository;

@Service
public class PaymentService implements IPaymentService{
	
	@Autowired
	private PaymentRepository repository;
	
	public PaymentService(PaymentRepository repository) {
		this.repository = repository;
	}

	@Override
	public void savePayment(PaymentDto dto) {
		PaymentEntity entity = EntityDtoUtil.convertPaymentDtoToEntity(dto);
		this.repository.savePayment(entity);
	}

}
