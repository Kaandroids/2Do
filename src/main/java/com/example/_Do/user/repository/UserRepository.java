package com.example._Do.user.repository;

import com.example._Do.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Data Access Object (DAO) for the User entity.
 * <p>
 * This interface extends JpaRepository to provide standard CRUD operations (Save, Find, Delete)
 * and defines custom query methods for User management.
 * Spring Data JPA automatically implements this interface at runtime.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their unique email address.
     * <p>
     * Used mainly during the authentication process to load user details.
     * </p>
     *
     * @param email The email address to search for.
     * @return An Optional containing the User if found, or empty if not.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user already exists with the given email.
     * <p>
     * This method is more efficient than findByEmail for validation purposes
     * (e.g., during registration) because it checks for existence without loading the full entity.
     * </p>
     *
     * @param email The email to check.
     * @return True if a user with this email exists, False otherwise.
     */
    boolean existsByEmail(String email);
}