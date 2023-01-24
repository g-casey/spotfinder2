package com.gcasey.spotfinder.features.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String email,
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
