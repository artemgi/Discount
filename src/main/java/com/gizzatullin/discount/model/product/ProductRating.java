package com.gizzatullin.discount.model.product;

import com.gizzatullin.discount.model.client.Client;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer rating;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
}
