package com.varsha.smartlibrary.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRequestDTO {

    @NotNull
    private Long memberId;

    @NotNull
    private Long bookId;
}