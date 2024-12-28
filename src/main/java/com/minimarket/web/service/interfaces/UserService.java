package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.UserRequest;
import com.minimarket.web.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);

    UserResponse getUserByEmail(String email);
}
