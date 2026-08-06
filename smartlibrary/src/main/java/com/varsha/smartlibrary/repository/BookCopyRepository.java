package com.varsha.smartlibrary.repository;

import com.varsha.smartlibrary.entity.BookCopy;
import com.varsha.smartlibrary.enums.BookCopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    Optional<BookCopy> findFirstByBookIdAndStatus(
            Long bookId,
            BookCopyStatus status
    );
    boolean existsByBookId(Long bookId);
}