package com.minimarket.web.controller.web;

import com.minimarket.web.dto.response.CartResponse;
import com.minimarket.web.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cart")
public class WebCartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public String viewCart(@PathVariable Long customerId, Model model) {
        CartResponse cart = cartService.getCartByCustomerId(customerId);
        model.addAttribute("cart", cart);
        return "cart/view"; // Template HTML: src/main/resources/templates/cart/view.html
    }
}
