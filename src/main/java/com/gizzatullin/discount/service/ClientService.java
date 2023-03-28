package com.gizzatullin.discount.service;

import com.gizzatullin.discount.dao.ClientRepository;
import com.gizzatullin.discount.model.client.Client;
import com.gizzatullin.discount.model.client.ClientStatictic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
	private final ClientRepository clientRepository;
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public void updateClientDiscount(Long clientId, BigDecimal discount1, BigDecimal discount2) {
		Optional<Client> clientOptional = clientRepository.findById(clientId);
		if (clientOptional.isPresent()) {
			Client client = clientOptional.get();
			client.setDiscount1(discount1);
			client.setDiscount2(discount2);
			clientRepository.save(client);
		}
	}

	public ClientStatictic getClientStatistics(Long clientId) {
		return new ClientStatictic();
	}
}
