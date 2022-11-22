package com.alten.hotel.booking.domain.port.repository;

import com.alten.hotel.booking.domain.entity.ClientEntity;

public interface IClientRepository {
	
	public ClientEntity saveClient(ClientEntity clientEntity);

}
