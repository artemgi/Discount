package com.gizzatullin.discount.model.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
	Product underTest = new Product();

	@BeforeEach
	void setUp() {

	}

	@Test
	void getPairRating() {
		// Given
		List<ProductRating> productRatingList = new ArrayList<>();
		ProductRating productRating1 = new ProductRating();
		productRating1.setRating(1);
		productRating1.setId(0L);
		ProductRating productRating2 = new ProductRating();
		productRating2.setRating(2);
		ProductRating productRating3 = new ProductRating();
		productRating3.setRating(3);
		ProductRating productRating4 = new ProductRating();
		productRating4.setRating(4);
		ProductRating productRating5 = new ProductRating();
		productRating5.setRating(5);
		productRating5.setId(4L);

		productRatingList.add(productRating1);
		productRatingList.add(productRating2);
		productRatingList.add(productRating2);
		productRatingList.add(productRating2);
		productRatingList.add(productRating3);
		productRatingList.add(productRating4);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		underTest.setRatings(productRatingList);

		//When
		Map<Integer, Integer> pairRating = underTest.getPairRating();

		//Then
		assertEquals(3, pairRating.get(2));
		assertEquals(5, pairRating.get(5));

	}

	@Test
	void getAverageRating() {
		//Given
		List<ProductRating> productRatingList = new ArrayList<>();
		ProductRating productRating3 = new ProductRating();
		productRating3.setRating(3);
		ProductRating productRating5 = new ProductRating();
		productRating5.setRating(5);
		productRatingList.add(productRating3);
		productRatingList.add(productRating3);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		productRatingList.add(productRating5);
		underTest.setRatings(productRatingList);

		//When
		BigDecimal averageRating = underTest.getAverageRating();

		//Then
		assertEquals("4.3", averageRating.toString());
	}
}