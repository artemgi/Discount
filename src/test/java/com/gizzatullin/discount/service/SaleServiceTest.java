package com.gizzatullin.discount.service;

import com.gizzatullin.discount.dao.ClientRepository;
import com.gizzatullin.discount.dao.HourDiscountRepository;
import com.gizzatullin.discount.model.HourDiscount;
import com.gizzatullin.discount.model.product.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
	public static final long PRODUCT_ID = 21L;
	@InjectMocks
	private SaleService underTest;
	@Mock
	private HourDiscountRepository hourDiscountRepository;

	@Test
	void getFinalPrice() {
		//Given
		Product product = new Product();
		product.setPrice(new BigDecimal("1000.00"));
		product.setId(PRODUCT_ID);
		HourDiscount hourDiscount = new HourDiscount();
		hourDiscount.setProduct(product);
		hourDiscount.setPercentDiscount(10);
		Mockito.when(hourDiscountRepository.findDiscountHours(PRODUCT_ID)).thenReturn(Optional.of(hourDiscount));

		//When
		BigDecimal finalPrice = underTest.getFinalPrice(product, 5);

		//Then
		assertEquals(new BigDecimal("4500.000"), finalPrice);
	}
}