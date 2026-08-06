package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.BookAuthor;
import com.varsha.smartlibrary.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}