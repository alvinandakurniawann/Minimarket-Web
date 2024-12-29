package com.minimarket.web.dto.response;

import com.minimarket.web.model.transaction.PaymentMethod;

import java.util.List;

public class TransactionResponse {

    private Long id;
    private String customerName;
    private List<Item> items;
    private String paymentMethod; // Representasi string untuk PaymentMethod
    private Double total;

    public TransactionResponse(Long id, String customerName, List<Item> items, PaymentMethod paymentMethod, Double total) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.paymentMethod = paymentMethod.name(); // Konversi enum ke string
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public static class Item {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Double pricePerUnit;
        private Double totalPrice;

        public Item(Long productId, String productName, Integer quantity, Double pricePerUnit, Double totalPrice) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
            this.totalPrice = totalPrice;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPricePerUnit() {
            return pricePerUnit;
        }

        public void setPricePerUnit(Double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
