package com.pierre.oceanblu.repository;

import com.pierre.oceanblu.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getById(Long existingProductID);
}
