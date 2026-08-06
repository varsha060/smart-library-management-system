package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {
}