package com.varsha.smartlibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    private String isbn;

    @NotBlank(message = "Book title is required")
    private String title;

    @NotNull(message = "Category is required")
    private Long categoryId;

    private String description;

    private Integer publishedYear;
}