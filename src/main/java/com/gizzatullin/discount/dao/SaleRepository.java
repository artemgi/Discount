package com.gizzatullin.discount.dao;

import com.gizzatullin.discount.model.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SaleRepository extends JpaRepository<Sale, Long> {
	Optional<Sale> findFirstByOrderByIdDesc();
}
