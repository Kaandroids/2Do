package com.example._Do.user.controller;

import com.example._Do.user.dto.UserCreateRequest;
import com.example._Do.user.dto.UserResponse;
import com.example._Do.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name="User Management", description="Admin operations for managing users.")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(
            summary="List all users",
            description="Returns a list of all registered users. Intended for Admin usage."
    )
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    @Operation(
            summary="Create User (Admin)",
            description="Creates a new user without signing them in. This is for Admins adding users manually."
    )
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}