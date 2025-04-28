package com.example.userservice.mapper;

import com.example.userservice.dto.*;
import com.example.userservice.model.User;

public class UserMapper {
    public static User toEntity(UserRequest request) {
        return new User.Builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
