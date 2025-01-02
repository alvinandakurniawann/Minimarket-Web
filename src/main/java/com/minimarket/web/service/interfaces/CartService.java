    package com.minimarket.web.service.interfaces;

    import com.minimarket.web.dto.request.CartRequest;
    import com.minimarket.web.dto.response.CartResponse;

    public interface CartService {
        CartResponse addToCart(CartRequest cartRequest);

        CartResponse getCartByCustomerId(Long customerId);

        void clearCart(Long customerId);
        
        
        
    }
