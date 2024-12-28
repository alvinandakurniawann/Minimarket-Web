package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.UserRequest;
import com.minimarket.web.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "auth/login"; // Mengarah ke src/main/resources/templates/auth/login.html
    }

    @GetMapping("/auth/register")
    public String registerPage(Model model) {
        model.addAttribute("userRequest", new UserRequest()); // Form registrasi
        return "auth/register"; // Mengarah ke src/main/resources/templates/auth/register.html
    }

    @PostMapping("/auth/register")
    public String handleRegister(@ModelAttribute UserRequest userRequest) {
        userService.registerUser(userRequest); // Registrasi pengguna baru
        return "redirect:/auth/login"; // Redirect ke halaman login setelah registrasi
    }
}
