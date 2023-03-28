package com.gizzatullin.discount.service;

import com.gizzatullin.discount.dao.HourDiscountRepository;
import com.gizzatullin.discount.dao.ProductRepository;
import com.gizzatullin.discount.model.HourDiscount;
import com.gizzatullin.discount.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DiscountService {
	private final ProductRepository productRepository;

	private  final HourDiscountRepository hourDiscountRepository;

	@Scheduled(cron = "@hourly")
	public void generateRandomDiscount() {
		List<Product> products = productRepository.findAll();
		Random random = new Random();
		Product randomProduct = products.get(random.nextInt(products.size()));

		Integer discount = random.nextInt(6) + 5;

		HourDiscount hourDiscount = new HourDiscount(randomProduct, discount);

		hourDiscountRepository.save(hourDiscount);
	}
}
