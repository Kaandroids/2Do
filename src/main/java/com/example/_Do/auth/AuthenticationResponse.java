package com.example._Do.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Response object containing the JWT access token.")
public class AuthenticationResponse {
    @Schema(
            description="JWT Access Token used for authorizing subsequent requests.",
            example="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ..."
    )
    private String token;
}