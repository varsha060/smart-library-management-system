package com.varsha.smartlibrary.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyRequestDTO {

    @NotNull(message = "Book ID is required")
    private Long bookId;

    private String status;
}