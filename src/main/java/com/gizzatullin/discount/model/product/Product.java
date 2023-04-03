package com.gizzatullin.discount.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private BigDecimal price;

	private String description;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<ProductRating> ratings;

	@JsonIgnore
	public BigDecimal getAverageRating(){
		OptionalDouble average = ratings.stream().mapToInt(ProductRating::getRating).average();
		if (average.isPresent()) {
			BigDecimal rating = BigDecimal.valueOf(average.getAsDouble());
			return rating.setScale(1, RoundingMode.FLOOR);
		} else {
			return null;
		}
	}

	@JsonIgnore
	public Map<Integer, Integer> getPairRating(){
		Map<Integer, Integer> pairRating= new java.util.HashMap<>(Map.of(
				1, 0,
				2, 0,
				3, 0,
				4, 0,
				5, 0));
		getRatings().forEach(s->pairRating.put(s.getRating(), pairRating.getOrDefault(s.getRating(), 0)+1));
		return pairRating;
	}

}
