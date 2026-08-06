package com.varsha.smartlibrary.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

    private Long id;

    private String isbn;

    private String title;

    private String categoryName;

    private String description;

    private Integer publishedYear;
}