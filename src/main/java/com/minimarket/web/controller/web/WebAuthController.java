package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.UserRequest;
import com.minimarket.web.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebAuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/login")
    public String loginPage() {
        return "auth/login"; // Template HTML untuk login page
    }

    @PostMapping("/auth/login")
    public String handleLogin(Authentication authentication) {
        User user = (User) authentication.getPrincipal(); // Menggunakan `user` untuk logging
        if ("ROLE_ADMIN".equals(authentication.getAuthorities().iterator().next().getAuthority())) {
            System.out.println("Admin logged in: " + user.getUsername());
            return "redirect:/admin/dashboard";
        } else {
            System.out.println("Customer logged in: " + user.getUsername());
            return "redirect:/customer/home";
        }
    }
    

    @GetMapping("/auth/register")
    public String registerPage(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String handleRegister(@ModelAttribute UserRequest userRequest) {
        userService.registerUser(userRequest);
        return "redirect:/auth/login";
    }
}
