package com.fitness.UserService.repo;

import com.fitness.UserService.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    boolean existsByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email);
}
