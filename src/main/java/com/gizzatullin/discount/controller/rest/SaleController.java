package com.gizzatullin.discount.controller.rest;

import com.gizzatullin.discount.controller.exceptions.SaleRegistrationException;
import com.gizzatullin.discount.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SaleController {
	@Autowired
	private SaleService saleService;

	//6. Регистрация продажи
	@PostMapping("/registration")
	public String registrationSale(@RequestParam Map<String, String> items,
									@RequestParam String totalCost) {
		items.remove("totalCost");
		try {
			return saleService.registrationSale(items, totalCost);
		} catch (SaleRegistrationException e){
			return e.getMessage();
		}
	}


}
