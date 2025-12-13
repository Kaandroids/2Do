package com.example._Do.user.dto;

import com.example._Do.user.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request object for Admin to create a new user with specific role")
public class UserCreateRequest {

    @Schema(
            description = "User's first name",
            example = "Admin"
    )
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(
            description = "User's last name",
            example = "User"
    )
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(
            description="User's email address",
            example="admin@company.com"
    )
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description="User's password",
            example="Secret@123"
    )
    @NotBlank(message="Password is required")
    private String password;

    @Schema(
            description="Role to be assigned",
            example="ROLE_ADMIN"
    )
    @NotNull(message="Role is required")
    private Role role;
}