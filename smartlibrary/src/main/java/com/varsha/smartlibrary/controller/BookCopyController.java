package com.varsha.smartlibrary.controller;

import com.varsha.smartlibrary.dto.BookCopyRequestDTO;
import com.varsha.smartlibrary.dto.BookCopyResponseDTO;
import com.varsha.smartlibrary.service.BookCopyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-copies")
@Tag(
        name = "Book Copies",
        description = "Manage physical copies of books"
)
public class BookCopyController {

    private final BookCopyService service;

    public BookCopyController(BookCopyService service) {
        this.service = service;
    }

    @Operation(summary = "Add a new book copy")
    @PostMapping
    public BookCopyResponseDTO create(
            @Valid @RequestBody BookCopyRequestDTO request) {

        return service.createBookCopy(request);
    }

    @Operation(summary = "Get all book copies")
    @GetMapping
    public List<BookCopyResponseDTO> getAll() {

        return service.getAllCopies();
    }

    @Operation(summary = "Get a book copy by ID")
    @GetMapping("/{id}")
    public BookCopyResponseDTO get(@PathVariable Long id) {

        return service.getCopy(id);
    }

    @Operation(summary = "Update a book copy")
    @PutMapping("/{id}")
    public BookCopyResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody BookCopyRequestDTO request) {

        return service.updateCopy(id, request);
    }

    @Operation(summary = "Delete a book copy")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        service.deleteCopy(id);

        return ResponseEntity.ok("Book Copy deleted successfully.");
    }
}