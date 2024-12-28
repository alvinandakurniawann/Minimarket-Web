package com.minimarket.web.controller.web;

import com.minimarket.web.dto.response.UserResponse;
import com.minimarket.web.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUsers(@RequestParam(required = false) String email, Model model) {
        if (email != null) {
            UserResponse user = userService.getUserByEmail(email);
            model.addAttribute("user", user);
        }
        return "users/list"; // Template HTML: src/main/resources/templates/users/list.html
    }
}
