package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.CartItemRequest;
import com.minimarket.web.dto.response.CartItemResponse;

public interface CartItemService {
    CartItemResponse addItemToCart(CartItemRequest cartItemRequest);

    void updateCartItem(Long cartItemId, Integer quantity);

    void removeCartItem(Long cartItemId);
}
