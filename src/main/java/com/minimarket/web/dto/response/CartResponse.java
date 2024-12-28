package com.minimarket.web.dto.response;

import java.util.List;

public class CartResponse {

    private Long id; // ID keranjang
    private String customerName; // Nama pelanggan
    private List<Item> items; // Daftar item dalam keranjang

    public CartResponse(Long id, String customerName, List<Item> items) {
        this.id = id;
        this.customerName = customerName;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
