package com.gizzatullin.discount.service;

import com.gizzatullin.discount.dao.HourDiscountRepository;
import com.gizzatullin.discount.dao.ProductRepository;
import com.gizzatullin.discount.model.HourDiscount;
import com.gizzatullin.discount.model.product.Product;
import com.gizzatullin.discount.model.product.ProductInfo;
import com.gizzatullin.discount.model.product.ProductRating;
import com.gizzatullin.discount.model.product.ProductStatistic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	private final HourDiscountRepository hourDiscountRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public ProductInfo getProductInfo(Long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			ProductInfo productInfo = new ProductInfo();
			productInfo.setDescription(product.getDescription());
			productInfo.setRating(product.getAverageRating());
			productInfo.setEstimates(product.getPairRating());
			List<ProductRating> ratings = product.getRatings();
			productInfo.setCurrentEstimate(ratings.get(ratings.size()-1).getRating());
			return productInfo;
		} else {
			return null;
		}
	}

	public BigDecimal getTotalCost(Map<String, String> items) {
		BigDecimal amount = new BigDecimal(0);
		for (Map.Entry<String, String> entry : items.entrySet()) {
			Long productId = Long.decode(String.valueOf(entry.getKey()));
			BigDecimal quantityProducts = BigDecimal.valueOf(Integer.parseInt(String.valueOf(entry.getValue())));
			Optional<Product> productOptional = productRepository.findById(productId);
			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				Optional<HourDiscount> byProductIdOrderByEndDiscount = hourDiscountRepository.findDiscountHours(productId);
				amount = amount
						.add(product.getPrice()
								.multiply(quantityProducts));

				if (byProductIdOrderByEndDiscount.isPresent()) {
					amount = amount
							.multiply(BigDecimal.valueOf(1 - 0.01 * byProductIdOrderByEndDiscount.get().getPercentDiscount()));
				}
			}
		}
		return amount;
	}

	public void rateProduct(long clientId, long productId, Integer rating) {
	}

	public ProductStatistic getProductStatistics(Long productId) {
		return new ProductStatistic();
	}
}
