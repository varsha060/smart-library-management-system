package com.varsha.smartlibrary.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResponseDTO {

    private Long transactionId;

    private String memberName;

    private String bookTitle;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private String status;
}