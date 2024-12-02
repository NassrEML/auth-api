package com.nassreml.auth.api.controllers.impl;

import com.nassreml.auth.api.common.dtos.TokenResponse;
import com.nassreml.auth.api.common.dtos.UserRequest;
import com.nassreml.auth.api.controllers.AuthApi;
import com.nassreml.auth.api.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }
}
