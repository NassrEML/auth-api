package com.nassreml.auth.api.controllers;

import com.nassreml.auth.api.common.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.API_ROUTE + ApiPathConstants.V1_ROUTE + ApiPathConstants.USER)
public interface UserApi {
    @GetMapping
    ResponseEntity<String> getUser(
            @RequestAttribute("X-User-Id") String userId
    );
}
