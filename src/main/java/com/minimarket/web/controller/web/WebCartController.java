package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.CartRequest;
import com.minimarket.web.dto.response.CartResponse;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.CartItemService;
import com.minimarket.web.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer/cart")
public class WebCartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String addToCart(@RequestParam Long productId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Only customers can add products to the cart"));

        CartRequest cartRequest = new CartRequest();
        cartRequest.setProductId(productId);
        cartRequest.setCustomerId(customer.getId());
        cartRequest.setQuantity(1); // Default quantity 1
        cartService.addToCart(cartRequest);

        return "redirect:/customer/cart/view/" + customer.getId();
    }

    @GetMapping("/view/{customerId}")
    public String viewCart(@PathVariable Long customerId, Model model) {
        CartResponse cart = cartService.getCartByCustomerId(customerId);
        model.addAttribute("cart", cart);
        model.addAttribute("customerId", customerId);
        return "customer/cart/view";
    }

    @PostMapping("/update/{cartItemId}")
    public String updateCartItem(@PathVariable Long cartItemId, @RequestParam Integer quantity, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        cartItemService.updateCartItem(cartItemId, quantity);
        return "redirect:/customer/cart/view/" + customer.getId();
    }
}
