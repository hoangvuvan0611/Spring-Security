package org.vvh.springsecurity.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record LoginResponse(
        String username,
        String accessToken,
        String refreshToken,
        List<String> roles
) {
}
