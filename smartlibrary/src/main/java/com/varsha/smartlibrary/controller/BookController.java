package com.varsha.smartlibrary.controller;

import com.varsha.smartlibrary.dto.BookRequestDTO;
import com.varsha.smartlibrary.dto.BookResponseDTO;
import com.varsha.smartlibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(
        name = "Books",
        description = "Manage library books"
)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public BookResponseDTO createBook(@Valid @RequestBody BookRequestDTO request) {
        return bookService.createBook(request);
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Get a book by ID")
    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Update a book")
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDTO request) {

        return bookService.updateBook(id, request);
    }

    @Operation(summary = "Delete a book")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return ResponseEntity.ok("Book deleted successfully.");
    }
}