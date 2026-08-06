package com.varsha.smartlibrary.controller;

import com.varsha.smartlibrary.dto.BorrowRequestDTO;
import com.varsha.smartlibrary.dto.BorrowResponseDTO;
import com.varsha.smartlibrary.dto.ReturnResponseDTO;
import com.varsha.smartlibrary.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
@Tag(
        name = "Borrowing",
        description = "Borrow and return library books"
)
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Operation(summary = "Borrow a book")
    @PostMapping
    public BorrowResponseDTO borrowBook(
            @Valid @RequestBody BorrowRequestDTO request) {

        return borrowService.borrowBook(request);
    }

    @Operation(summary = "Return a borrowed book")
    @PostMapping("/return/{transactionId}")
    public ReturnResponseDTO returnBook(
            @PathVariable Long transactionId) {

        return borrowService.returnBook(transactionId);
    }
}