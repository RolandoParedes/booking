package com.alten.hotel.booking.domain.usecases;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alten.hotel.booking.domain.dto.ClientDto;
import com.alten.hotel.booking.domain.entity.ClientEntity;
import com.alten.hotel.booking.infraestructure.adapter.repository.ClientRepository;

public class ClientServiceTest {

	@Nested
	class whenSaveClient{
		
		ClientService clientService;
		
		@Mock
		ClientRepository repository;
		
		@Mock
		ClientDto clientDto;
		
		@Mock
		ClientEntity clientEntity;
	
		@BeforeEach
		void init() throws Exception {
			MockitoAnnotations.initMocks(this);
			clientService = spy(new ClientService(repository));
		}
		
		
		@Test
		void saveClient() {
			clientService.saveClient(clientDto);
		}
	
	}

}
