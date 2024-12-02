package com.nassreml.auth.api.services;

import com.nassreml.auth.api.common.dtos.TokenResponse;
import com.nassreml.auth.api.common.dtos.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);
}
