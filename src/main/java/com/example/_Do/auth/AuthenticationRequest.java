package com.example._Do.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Request object containing user credentials for login.")
public class AuthenticationRequest {
    @Schema(
            description="Registered email address",
            example="john.doe@example.com"
    )
    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private String email;

    @Schema(
            description="User's password",
            example="Secret@123"
    )
    @NotBlank(message="Password is required")
    private String password;
}
