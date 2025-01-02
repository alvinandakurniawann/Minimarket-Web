package com.minimarket.web.service.impl;

import com.minimarket.web.dto.request.CartItemRequest;
import com.minimarket.web.dto.response.CartItemResponse;
import com.minimarket.web.model.cart.Cart;
import com.minimarket.web.model.cart.CartItem;
import com.minimarket.web.model.product.Product;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.CartItemRepository;
import com.minimarket.web.repository.CartRepository;
import com.minimarket.web.repository.ProductRepository;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CartItemResponse addItemToCart(CartItemRequest cartItemRequest) {
        Customer customer = (Customer) userRepository.findById(cartItemRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer with ID " + cartItemRequest.getCustomerId() + " not found"));

        Cart cart = cartRepository.findByCustomerId(customer.getId());
        if (cart == null) {
            throw new RuntimeException("Cart not found for Customer ID: " + customer.getId());
        }

        Product product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartItemRequest.getProductId()));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemRequest.getQuantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return new CartItemResponse(
                savedCartItem.getId(),
                savedCartItem.getProduct().getName(),
                savedCartItem.getQuantity(),
                savedCartItem.getProduct().getPrice(),
                savedCartItem.getQuantity() * savedCartItem.getProduct().getPrice()
        );
    }

    @Override
    @Transactional
    public void updateCartItem(Long cartItemId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
    
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
    
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
    
    

    @Override
    @Transactional
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
