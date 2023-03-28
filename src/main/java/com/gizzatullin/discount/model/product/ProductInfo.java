package com.gizzatullin.discount.model.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductInfo {

	private String description;

	private BigDecimal rating;

	private Map<Integer, Integer> estimates;

	private Integer currentEstimate;

}
