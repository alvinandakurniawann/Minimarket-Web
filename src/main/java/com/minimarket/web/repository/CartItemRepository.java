package com.minimarket.web.repository;

import com.minimarket.web.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsByIdAndCartCustomerId(Long cartItemId, Long customerId);


}


