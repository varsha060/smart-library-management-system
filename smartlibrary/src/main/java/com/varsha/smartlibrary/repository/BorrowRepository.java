package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowTransaction, Long> {
}