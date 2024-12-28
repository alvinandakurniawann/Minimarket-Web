package com.minimarket.web.controller.api;

import com.minimarket.web.dto.request.UserRequest;
import com.minimarket.web.dto.response.UserResponse;
import com.minimarket.web.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-admin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.createAdmin(userRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.registerUser(userRequest);
        return ResponseEntity.ok(response);
    }
}
