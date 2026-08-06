package com.varsha.smartlibrary.service;
import com.varsha.smartlibrary.dto.UserRequestDTO;
import com.varsha.smartlibrary.dto.UserResponseDTO;
import com.varsha.smartlibrary.entity.User;
import com.varsha.smartlibrary.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.varsha.smartlibrary.exception.DuplicateResourceException;
import com.varsha.smartlibrary.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCreatedAt()
        );
    }
}