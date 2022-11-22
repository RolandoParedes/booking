package com.alten.hotel.booking.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.entity.ClientEntity;
import com.alten.hotel.booking.domain.port.service.IClientService;
import com.alten.hotel.booking.domain.util.EntityDtoUtil;
import com.alten.hotel.booking.infraestructure.adapter.repository.ClientRepository;

@Service
public class ClientService implements IClientService{
	
	@Autowired
	private ClientRepository repository;
	
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveClient(ClientDto clientDto) {
		ClientEntity entity = EntityDtoUtil.convertClientDtoToEntity(clientDto);
		this.repository.saveClient(entity);
		
	}

	
	
}
