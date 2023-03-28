package com.gizzatullin.discount.service.soap;

import com.gizzatullin.discount.dao.ClientRepository;
import com.gizzatullin.discount.model.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@WebService(
		serviceName = "Client",
		portName = "Port",
		targetNamespace = "http://service.ws.sample/",
		endpointInterface = "com.gizzatullin.discount.service.soap.ClientService"
)
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	private final ClientRepository clientRepository;


	@Override
	public String sayHello(String name) {
		return "Hello" + name;
	}

	@Override
	public List<Client> getClients() {
		return clientRepository.findAll();
	}
}
