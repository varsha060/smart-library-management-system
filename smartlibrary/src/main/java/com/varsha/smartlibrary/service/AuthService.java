package com.varsha.smartlibrary.service;

import com.varsha.smartlibrary.dto.LoginRequestDTO;
import com.varsha.smartlibrary.dto.LoginResponseDTO;
import com.varsha.smartlibrary.entity.User;
import com.varsha.smartlibrary.exception.ResourceNotFoundException;
import com.varsha.smartlibrary.repository.UserRepository;
import com.varsha.smartlibrary.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new ResourceNotFoundException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponseDTO(token);
    }
}