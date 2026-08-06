package com.varsha.smartlibrary.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyResponseDTO {

    private Long id;

    private String bookTitle;

    private String status;
}