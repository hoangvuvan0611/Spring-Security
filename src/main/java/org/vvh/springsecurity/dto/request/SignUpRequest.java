package org.vvh.springsecurity.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record SignUpRequest(
        @NotBlank(message = "Username must not be blank")
        String username,
        String password,
        Set<String> role
) {
}
