package com.gizzatullin.discount.controller.rest;

import com.gizzatullin.discount.model.client.Client;
import com.gizzatullin.discount.model.client.ClientStatictic;
import com.gizzatullin.discount.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientRestController {
	@Autowired
	private ClientService clientService;

	//1. список клиентов (все атрибуты).
	@GetMapping("")
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	//2. изменение индивидуальных скидок клиента (входные параметры: идентификатор, скидка 1, скидка 2).
	@PutMapping("/{clientId}")
	public void updateClientDiscount(@PathVariable Long clientId,
									 @RequestParam BigDecimal discount1,
									 @RequestParam BigDecimal discount2) {
		clientService.updateClientDiscount(clientId, discount1, discount2);
	}

	//8. Получение статистики
	@GetMapping("/statistic/{clientId}")
	public ClientStatictic getClientStatistics(@PathVariable Long clientId) {
		return clientService.getClientStatistics(clientId);
	}

}
