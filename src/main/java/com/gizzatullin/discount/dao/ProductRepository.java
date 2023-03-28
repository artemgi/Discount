package com.gizzatullin.discount.dao;

import com.gizzatullin.discount.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
