package com.varsha.smartlibrary.controller;

import com.varsha.smartlibrary.dto.LoginRequestDTO;
import com.varsha.smartlibrary.dto.LoginResponseDTO;
import com.varsha.smartlibrary.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(
        name = "Authentication",
        description = "Login and JWT authentication"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Login and generate JWT token")
    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request) {

        return authService.login(request);
    }
}