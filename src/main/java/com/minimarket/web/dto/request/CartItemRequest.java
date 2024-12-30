package com.minimarket.web.dto.request;

import jakarta.validation.constraints.NotNull;

public class CartItemRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long productId;

    @NotNull
    private Integer quantity;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
