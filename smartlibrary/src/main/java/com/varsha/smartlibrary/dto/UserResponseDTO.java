package com.varsha.smartlibrary.dto;

import lombok.*;
import com.varsha.smartlibrary.enums.Role;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String email;

    private Role role;

    private LocalDateTime createdAt;
}