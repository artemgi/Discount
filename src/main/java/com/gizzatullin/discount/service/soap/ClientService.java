package com.gizzatullin.discount.service.soap;

import com.gizzatullin.discount.model.client.Client;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://service.ws.sample/", name = "ClientService")
public interface ClientService {

	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(
			localName = "sayHello",
			targetNamespace = "http://service.ws.sample/",
			className = "com.gizzatullin.discount.service.soap.RequestSayHello")
	@WebMethod(action = "urn:SayHello")
	@ResponseWrapper(
			localName = "sayHelloResponse",
			targetNamespace = "http://service.ws.sample/",
			className = "com.gizzatullin.discount.service.soap.SayHelloResponse")
	String sayHello(@WebParam(name = "name", targetNamespace = "") String name);

	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(
			localName = "getClients",
			targetNamespace = "http://service.ws.sample/",
			className = "com.gizzatullin.discount.service.soap.RequestGetClients")
	@WebMethod(action = "urn:SayHello")
	@ResponseWrapper(
			localName = "getClientsResponse",
			targetNamespace = "http://service.ws.sample/",
			className = "com.gizzatullin.discount.service.soap.GetClientsResponse")
	List<Client> getClients();
}
