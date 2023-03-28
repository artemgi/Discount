package com.gizzatullin.discount.model.client;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "discount_1")
	private BigDecimal discount1;

	@Column(name = "discount_2")
	private BigDecimal discount2;
}
