package org.vvh.springsecurity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vvh.springsecurity.dto.request.LoginRequest;
import org.vvh.springsecurity.dto.request.SignUpRequest;
import org.vvh.springsecurity.dto.response.ResponseData;
import org.vvh.springsecurity.dto.response.ResponseError;
import org.vvh.springsecurity.service.AuthService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseData<?> createUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        log.info("Creating user: {}", signUpRequest.username());
        try {
            authService.registerUser(signUpRequest);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create user successfully", null);
        } catch (Exception e) {
            log.info("Creating error {}: ", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Create user error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseData<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("Login: {}", loginRequest.username());
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Login successfully", authService.loginUser(loginRequest));
        } catch (Exception ex) {
            log.info("Login error {}: ", ex.getMessage(), ex.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Login error: " + ex.getMessage());
        }
    }
}
