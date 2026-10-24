package com.rodrigs.inventory_control.controller;

import com.rodrigs.inventory_control.dto.AuthResponse;
import com.rodrigs.inventory_control.dto.UserRequest;
import com.rodrigs.inventory_control.dto.UserResponse;
import com.rodrigs.inventory_control.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse registerUser(@Valid @RequestBody UserRequest request) {
        return userService.registerUser(request);
    }
}
