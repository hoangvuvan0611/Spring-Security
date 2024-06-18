package org.vvh.springsecurity.dto.request;

public record LoginRequest(
        String username,
        String password
) {
}
