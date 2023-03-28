package com.gizzatullin.discount.controller.rest;

import com.gizzatullin.discount.model.client.ClientStatictic;
import com.gizzatullin.discount.model.product.Product;
import com.gizzatullin.discount.model.product.ProductInfo;
import com.gizzatullin.discount.model.product.ProductStatistic;
import com.gizzatullin.discount.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductRestController {
	@Autowired
	private ProductService productService;

	//3. список товаров (идентификатор, наименование, цена).
	@GetMapping("")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	//4. получение дополнительной информации о товаре
	@GetMapping("/{productId}")
	public ProductInfo getProductInfo(@PathVariable Long productId) {
		return productService.getProductInfo(productId);
	}

	//5. Запрос итоговой стоимости
	@GetMapping("/totalCost")
	public BigDecimal getTotalCost(@RequestParam Map<String, String> items) {
		return productService.getTotalCost(items);
	}

	//7. Оценка товара
	@PostMapping("/rateProduct")
	public void rateProduct(@RequestParam("clientId") long clientId,
							@RequestParam("productId") long productId,
							@RequestParam(value = "rating", required = false) Integer rating) {
		productService.rateProduct(clientId, productId, rating);
	}

	//8. Получение статистики
	@GetMapping("/statistic/{productId}")
	public ProductStatistic getProductStatistics(@PathVariable Long productId) {
		return productService.getProductStatistics(productId);
	}
}
