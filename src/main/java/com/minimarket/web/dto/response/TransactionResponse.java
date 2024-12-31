package com.minimarket.web.dto.response;

import com.minimarket.web.model.transaction.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionResponse {

    private Long id;
    private String customerName;
    private String customerEmail;
    private PaymentMethod paymentMethod;
    private Double total;
    private LocalDateTime createdAt;
    private List<Item> items;

    public TransactionResponse(Long id, String customerName, String customerEmail, PaymentMethod paymentMethod, Double total, LocalDateTime createdAt, List<Item> items) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.createdAt = createdAt;
        this.items = items;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private Long productId;
        private String productName;
        private String productImage; // Tambahkan untuk gambar produk
        private Integer quantity;
        private Double price;
        private Double subtotal;

        public Item(Long productId, String productName, String productImage, Integer quantity, Double price, Double subtotal) {
            this.productId = productId;
            this.productName = productName;
            this.productImage = productImage;
            this.quantity = quantity;
            this.price = price;
            this.subtotal = subtotal;
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

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
    }
}
