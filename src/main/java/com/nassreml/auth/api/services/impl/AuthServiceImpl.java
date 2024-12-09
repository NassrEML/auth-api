package com.nassreml.auth.api.services.impl;

import com.nassreml.auth.api.common.dtos.TokenResponse;
import com.nassreml.auth.api.common.dtos.UserRequest;
import com.nassreml.auth.api.common.entities.UserEntity;
import com.nassreml.auth.api.repositories.UserRepository;
import com.nassreml.auth.api.services.AuthService;
import com.nassreml.auth.api.services.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(user -> jwtService.generateToken(user.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public TokenResponse login(UserRequest userRequest) {
        return userRepository.findByEmail(userRequest.getEmail())
                .filter(user -> passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getId()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    private UserEntity mapToEntity(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        return UserEntity.builder()
                .email(userRequest.getEmail())
                .password(encodedPassword)
                .role("USER")
                .build();
    }
}
