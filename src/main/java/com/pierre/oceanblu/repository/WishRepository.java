package com.pierre.oceanblu.repository;

import com.pierre.oceanblu.model.Product;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

    Wish getByUserAndProduct(User user, Product product);
    boolean existsByUserAndProduct(User user, Product product);
}