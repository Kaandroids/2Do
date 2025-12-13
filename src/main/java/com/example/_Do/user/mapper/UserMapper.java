package com.example._Do.user.mapper;

import com.example._Do.user.dto.RegisterRequest;
import com.example._Do.user.dto.UserCreateRequest;
import com.example._Do.user.dto.UserResponse;
import com.example._Do.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between User Entities and DTOs.
 * <p>
 * Uses MapStruct to generate implementation code at compile time.
 * The componentModel="spring" attribute allows this mapper to be injected
 * as a Spring Bean (@Autowired) into services.
 * </p>
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE // Ignore fields that don't match (prevents compiler warnings)
)
public interface UserMapper {

    /**
     * Converts a public RegisterRequest DTO to a User Entity.
     * <p>
     * Note: The password in the entity will be the RAW password from the request.
     * </p>
     *
     * @param registerRequest The DTO containing public registration data.
     * @return The User entity.
     */
    User toEntity(RegisterRequest registerRequest);

    /**
     * Converts the admin-specific UserCreateRequest DTO to a User Entity.
     * <p>
     * Unlike the public registration mapper, this method maps the 'role' field
     * directly from the request, allowing admins to assign specific roles (e.g., ADMIN).
     * Note: The password is still raw here.
     * </p>
     *
     * @param request The admin request containing user details and specific role.
     * @return The User entity populated with data including the assigned role.
     */
    User toEntity(UserCreateRequest request);

    /**
     * Converts a User Entity to a UserResponse DTO.
     * <p>
     * This ensures that sensitive information (like passwords) is not exposed,
     * as UserResponse does not contain a password field.
     * </p>
     *
     * @param user The User entity from the database.
     * @return The safe UserResponse DTO.
     */
    UserResponse toResponse(User user);

}