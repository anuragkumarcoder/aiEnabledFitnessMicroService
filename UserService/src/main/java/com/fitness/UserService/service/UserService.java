package com.fitness.UserService.service;

import com.fitness.UserService.dto.RegisterRequest;
import com.fitness.UserService.dto.UserResponse;
import com.fitness.UserService.model.User;
import com.fitness.UserService.repo.UserRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;
    public UserResponse register(@Valid RegisterRequest registerRequest) {
        if(userRepo.existsByEmail(registerRequest.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new  User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        User savedUser=userRepo.save(user);
        UserResponse userResponse=new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setRole(savedUser.getRole());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        return userResponse;


    }

    public UserResponse getUserProfile(String userId) {
        User savedUser=userRepo.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        UserResponse userResponse=new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setRole(savedUser.getRole());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        return userResponse;

    }
}
