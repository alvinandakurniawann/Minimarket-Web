package com.minimarket.web.controller.api;

import com.minimarket.web.dto.request.CartRequest;
import com.minimarket.web.dto.response.CartResponse;
import com.minimarket.web.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest cartRequest) {
        CartResponse response = cartService.addToCart(cartRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long customerId) {
        CartResponse response = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long customerId) {
        cartService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }
}
