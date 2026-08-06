package com.varsha.smartlibrary.entity;

import com.varsha.smartlibrary.enums.BookCopyStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_copies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "copy_status", nullable = false)
    private BookCopyStatus status = BookCopyStatus.AVAILABLE;
}