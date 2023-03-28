package com.gizzatullin.discount.dao;

import com.gizzatullin.discount.model.HourDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HourDiscountRepository extends JpaRepository<HourDiscount, Long> {
	@Query("SELECT hd FROM HourDiscount hd join hd.product pr WHERE " +
			"hd.startDiscount <= CURRENT_TIMESTAMP and hd.endDiscount >= CURRENT_TIMESTAMP and pr.id = :productId")
	Optional<HourDiscount> findDiscountHours(@Param("productId") Long productId);
}
