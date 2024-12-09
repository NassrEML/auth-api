package com.nassreml.auth.api.controllers.impl;

import com.nassreml.auth.api.controllers.UserApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
    @Override
    public ResponseEntity<String> getUser(String userId) {
        return ResponseEntity.ok("User ID: " + userId);
    }
}
