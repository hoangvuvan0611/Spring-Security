package org.vvh.springsecurity.service;

import org.vvh.springsecurity.dto.request.LoginRequest;
import org.vvh.springsecurity.dto.request.SignUpRequest;
import org.vvh.springsecurity.dto.response.LoginResponse;
import org.vvh.springsecurity.entity.Role;

import java.util.List;

public interface AuthService {
    void registerUser(SignUpRequest registerRequest);
    LoginResponse loginUser(LoginRequest loginRequest);
}
