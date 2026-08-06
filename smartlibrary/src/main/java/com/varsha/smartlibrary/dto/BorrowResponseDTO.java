package com.varsha.smartlibrary.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowResponseDTO {

    private Long transactionId;

    private String memberName;

    private String bookTitle;

    private Long copyId;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private String status;
}