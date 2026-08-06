package com.varsha.smartlibrary.controller;

import com.varsha.smartlibrary.dto.UserRequestDTO;
import com.varsha.smartlibrary.dto.UserResponseDTO;
import com.varsha.smartlibrary.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(
        name = "Users",
        description = "User registration and management"
)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public UserResponseDTO registerUser(
            @Valid @RequestBody UserRequestDTO request) {

        return userService.registerUser(request);
    }
}