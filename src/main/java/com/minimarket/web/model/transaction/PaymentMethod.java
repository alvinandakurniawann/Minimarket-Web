package com.minimarket.web.model.transaction;

public enum PaymentMethod {
    CASH("Cash Payment"),
    TRANSFER("Bank Transfer"),
    QRIS("QRIS Payment");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
