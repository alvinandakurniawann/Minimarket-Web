package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.UserRequest;
import com.minimarket.web.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponse registerUser(UserRequest userRequest);

    UserResponse getUserByEmail(String email);
}
