package com.minimarket.web.service.impl;

import com.minimarket.web.dto.request.CartRequest;
import com.minimarket.web.dto.response.CartResponse;
import com.minimarket.web.model.cart.Cart;
import com.minimarket.web.model.cart.CartItem;
import com.minimarket.web.model.product.Product;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.CartRepository;
import com.minimarket.web.repository.ProductRepository;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.CartService;
import com.minimarket.web.util.CurrencyFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CartResponse addToCart(CartRequest cartRequest) {
        Customer customer = (Customer) userRepository.findById(cartRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Cart cart = cartRepository.findByCustomerId(customer.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart.setItems(new ArrayList<>());
        }

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartRequest.getQuantity());
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartRequest.getQuantity());
            cart.getItems().add(cartItem);
        }

        Cart savedCart = cartRepository.save(cart);

        return new CartResponse(
                savedCart.getId(),
                savedCart.getCustomer().getFullName(),
                savedCart.getItems().stream().map(item -> new CartResponse.Item(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getImageUrl(),
                        item.getQuantity(),
                        CurrencyFormatter.formatToRupiah(item.getProduct().getPrice()),
                        CurrencyFormatter.formatToRupiah(item.getQuantity() * item.getProduct().getPrice())
                )).collect(Collectors.toList()),
                CurrencyFormatter.formatToRupiah(savedCart.getItems().stream()
                        .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                        .sum())
        );
    }

    @Override
    public CartResponse getCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for customer");
        }

        double totalPrice = cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();

        return new CartResponse(
                cart.getId(),
                cart.getCustomer().getFullName(),
                cart.getItems().stream().map(item -> new CartResponse.Item(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getImageUrl(),
                        item.getQuantity(),
                        CurrencyFormatter.formatToRupiah(item.getProduct().getPrice()),
                        CurrencyFormatter.formatToRupiah(item.getQuantity() * item.getProduct().getPrice())
                )).collect(Collectors.toList()),
                CurrencyFormatter.formatToRupiah(totalPrice)
        );
    }

    @Override
    @Transactional
    public void clearCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }
}
