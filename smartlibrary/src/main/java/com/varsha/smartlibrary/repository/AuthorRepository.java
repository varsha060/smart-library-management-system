package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}