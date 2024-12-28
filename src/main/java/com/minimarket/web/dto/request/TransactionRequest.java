package com.minimarket.web.dto.request;

import jakarta.validation.constraints.NotNull;

public class TransactionRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private String paymentMethod; // cash, debit, wallet

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
