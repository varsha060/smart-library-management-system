package com.varsha.smartlibrary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id")
    private Author author;
}