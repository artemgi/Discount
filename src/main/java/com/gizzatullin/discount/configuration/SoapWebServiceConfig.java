package com.gizzatullin.discount.configuration;
import javax.xml.ws.Endpoint;

import com.gizzatullin.discount.dao.ClientRepository;
import com.gizzatullin.discount.service.soap.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SoapWebServiceConfig {
	@Autowired
	private Bus bus;
	private final ClientRepository clientRepository;

	@Bean
	public Endpoint helloEndpoint(){
		EndpointImpl endpoint = new EndpointImpl(bus, new ClientServiceImpl(clientRepository));
		endpoint.publish("/Client");

		return endpoint;
	}
}
