package com.alten.hotel.booking.infraestructure.adapter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alten.hotel.booking.domain.entity.ClientEntity;
import com.alten.hotel.booking.domain.port.repository.IClientRepository;
import com.alten.hotel.booking.infraestructure.adapter.repository.jpa.ClientRepositoryJPA;

@Component
public class ClientRepository implements IClientRepository{
	
	@Autowired
	private ClientRepositoryJPA repository;

	@Override
	public ClientEntity saveClient(ClientEntity clientEntity) {
		return this.repository.saveAndFlush(clientEntity);
	}

}
