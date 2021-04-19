package com.pierre.oceanblu.repository;

import com.pierre.oceanblu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction getById(Long id);
}
