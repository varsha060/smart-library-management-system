package com.varsha.smartlibrary.service;

import com.varsha.smartlibrary.dto.BookRequestDTO;
import com.varsha.smartlibrary.dto.BookResponseDTO;
import com.varsha.smartlibrary.entity.Book;
import com.varsha.smartlibrary.entity.Category;
import com.varsha.smartlibrary.exception.DuplicateResourceException;
import com.varsha.smartlibrary.exception.ResourceNotFoundException;
import com.varsha.smartlibrary.repository.BookCopyRepository;
import com.varsha.smartlibrary.repository.BookRepository;
import com.varsha.smartlibrary.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookCopyRepository bookCopyRepository;

    public BookService(BookRepository bookRepository,
                       CategoryRepository categoryRepository,
                       BookCopyRepository bookCopyRepository) {

        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public BookResponseDTO createBook(BookRequestDTO request) {

        if (request.getIsbn() != null &&
                bookRepository.findByIsbn(request.getIsbn()).isPresent()) {
            throw new DuplicateResourceException("ISBN already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setCategory(category);
        book.setDescription(request.getDescription());
        book.setPublishedYear(request.getPublishedYear());

        Book savedBook = bookRepository.save(book);

        return new BookResponseDTO(
                savedBook.getId(),
                savedBook.getIsbn(),
                savedBook.getTitle(),
                savedBook.getCategory().getName(),
                savedBook.getDescription(),
                savedBook.getPublishedYear()
        );
    }

    public List<BookResponseDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getCategory().getName(),
                        book.getDescription(),
                        book.getPublishedYear()
                ))
                .toList();
    }

    public BookResponseDTO getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found."));

        return new BookResponseDTO(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getCategory().getName(),
                book.getDescription(),
                book.getPublishedYear()
        );
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found."));

        if (request.getIsbn() != null &&
                bookRepository.findByIsbn(request.getIsbn()).isPresent() &&
                !request.getIsbn().equals(book.getIsbn())) {

            throw new DuplicateResourceException("ISBN already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setCategory(category);
        book.setDescription(request.getDescription());
        book.setPublishedYear(request.getPublishedYear());

        Book updatedBook = bookRepository.save(book);

        return new BookResponseDTO(
                updatedBook.getId(),
                updatedBook.getIsbn(),
                updatedBook.getTitle(),
                updatedBook.getCategory().getName(),
                updatedBook.getDescription(),
                updatedBook.getPublishedYear()
        );
    }

    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found."));

        if (bookCopyRepository.existsByBookId(id)) {
            throw new DuplicateResourceException(
                    "Cannot delete book because book copies exist.");
        }

        bookRepository.delete(book);
    }
}