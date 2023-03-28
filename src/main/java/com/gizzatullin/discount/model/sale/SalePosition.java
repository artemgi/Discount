package com.gizzatullin.discount.model.sale;

import com.gizzatullin.discount.model.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class SalePosition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id")
	private Sale sale;

	private Integer quantity;

	private BigDecimal initialPrice;

	private BigDecimal finalPrice;

	private BigDecimal discount;
}
