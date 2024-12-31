package com.minimarket.web.controller.web;

import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/profile")
public class WebProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view")
    public String viewProfile(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        model.addAttribute("customer", customer);
        return "customer/profile/view";
    }
}
