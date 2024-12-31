package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.ProfileUpdateRequest;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer/profile")
public class WebProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    // View Profile
    @GetMapping("/view")
    public String viewProfile(Authentication authentication, Model model) {
        String email = authentication.getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        model.addAttribute("customer", customer);
        return "customer/profile/view";
    }

    // Edit Profile Page
    @GetMapping("/edit")
    public String editProfile(Authentication authentication, Model model) {
        String email = authentication.getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        model.addAttribute("customer", customer);
        return "customer/profile/edit";
    }

    // Update Profile
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute ProfileUpdateRequest profileUpdateRequest, Authentication authentication) {
        String email = authentication.getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update user details
        customer.setFullName(profileUpdateRequest.getFullName());
        customer.setAddress(profileUpdateRequest.getAddress());
        customer.setGender(profileUpdateRequest.getGender());
        customer.setPhone(profileUpdateRequest.getPhone());

        customerRepository.save(customer);
        return "redirect:/customer/profile/view";
    }
}
