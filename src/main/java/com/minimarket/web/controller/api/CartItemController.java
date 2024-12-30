package com.minimarket.web.controller.api;

import com.minimarket.web.dto.request.CartItemRequest;
import com.minimarket.web.dto.response.CartItemResponse;
import com.minimarket.web.service.interfaces.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemResponse> addItemToCart(@RequestBody CartItemRequest cartItemRequest) {
        CartItemResponse response = cartItemService.addItemToCart(cartItemRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCartItem(@PathVariable Long id, @RequestParam Integer quantity) {
        cartItemService.updateCartItem(id, quantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        cartItemService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
