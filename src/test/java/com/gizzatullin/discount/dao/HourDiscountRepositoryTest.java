package com.gizzatullin.discount.dao;

import com.gizzatullin.discount.model.HourDiscount;
import com.gizzatullin.discount.model.product.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HourDiscountRepositoryTest {
	public static final int PERCENT_DISCOUNT = 25;
	@Autowired
	HourDiscountRepository underTest;
	@Autowired
	ProductRepository productRepository;

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
		productRepository.deleteAll();
	}

	@Test
	void findDiscountHoursDoesExist() {
		//given
		Product product = new Product();
		productRepository.save(product);
		HourDiscount hourDiscount = new HourDiscount();
		LocalDateTime localDateTime = LocalDateTime.now();
		hourDiscount.setStartDiscount(localDateTime.minusHours(2));
		hourDiscount.setEndDiscount(localDateTime.plusHours(2));
		hourDiscount.setPercentDiscount(PERCENT_DISCOUNT);
		hourDiscount.setProduct(product);
		underTest.save(hourDiscount);

		//when
		underTest.findDiscountHours(product.getId());

		//then
		assertFalse(underTest.findDiscountHours(product.getId()).isEmpty());
	}

	@Test
	void findDiscountHoursDoesNotExist() {
		//when
		underTest.findDiscountHours(1L);

		//then
		assertTrue(underTest.findDiscountHours(1L).isEmpty());
	}
}