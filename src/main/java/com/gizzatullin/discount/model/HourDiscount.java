package com.gizzatullin.discount.model;

import com.gizzatullin.discount.model.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class HourDiscount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	private LocalDateTime startDiscount;

	private LocalDateTime endDiscount;

	private Integer percentDiscount;

	public HourDiscount(Product product, Integer percentDiscount) {
		this.startDiscount = LocalDateTime.now();
		this.endDiscount = startDiscount.plusHours(1);
		this.product = product;
		this.percentDiscount = percentDiscount;
	}

	public HourDiscount() {

	}
}
