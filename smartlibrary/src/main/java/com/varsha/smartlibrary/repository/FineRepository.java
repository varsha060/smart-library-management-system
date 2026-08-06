package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
}