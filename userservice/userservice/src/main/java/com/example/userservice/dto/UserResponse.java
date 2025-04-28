package com.example.userservice.dto;

import io.swagger.v3.oas.annotations.responses.ApiResponse;


public record UserResponse(
        Long id,
        String name,
        String email,
        String phone
) {}
