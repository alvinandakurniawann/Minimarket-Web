package com.minimarket.web.service.impl;

import com.minimarket.web.dto.request.UserRequest;
import com.minimarket.web.dto.response.UserResponse;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        Customer user = new Customer();
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setAddress(userRequest.getAddress());
        user.setRole("CUSTOMER");

        Customer savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getAddress()
        );
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        Customer user = (Customer) userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getAddress()
        );
    }
}
