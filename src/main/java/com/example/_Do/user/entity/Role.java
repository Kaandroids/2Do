package com.example._Do.user.entity;

/**
 * Defines the security roles available in the application.
 * <p>
 * These roles are used to control access to specific endpoints or services
 * via Spring Security's authorization mechanisms (e.g., @PreAuthorize).
 * </p>
 */
public enum Role {
    /**
     * Administrator role with full access to all resources.
     * Can manage users, view system logs, etc.
     */
    ROLE_ADMIN,

    /**
     * Standard user role.
     * Can only access and manage their own data (tasks, profile).
     */
    ROLE_USER,
}
