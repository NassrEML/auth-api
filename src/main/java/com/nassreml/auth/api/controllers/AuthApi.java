package com.nassreml.auth.api.controllers;

import com.nassreml.auth.api.common.constants.ApiPathConstants;
import com.nassreml.auth.api.common.dtos.TokenResponse;
import com.nassreml.auth.api.common.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.API_ROUTE + ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

    @PostMapping("/register")
    ResponseEntity<TokenResponse> createUser(
            @RequestBody
            @Valid UserRequest userRequest
    );

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(
            @RequestBody
            @Valid UserRequest userRequest
    );
}
