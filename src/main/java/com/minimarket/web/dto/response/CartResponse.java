package com.minimarket.web.dto.response;

import java.util.List;

public class CartResponse {

    private Long id; // ID keranjang
    private String customerName; // Nama pelanggan
    private List<Item> items; // Daftar item dalam keranjang
    private double totalPrice; // Total harga keranjang

    // Konstruktor dengan totalPrice
    public CartResponse(Long id, String customerName, List<Item> items, double totalPrice) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Konstruktor tanpa totalPrice
    public CartResponse(Long id, String customerName, List<Item> items) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = items.stream()
                .mapToDouble(Item::getTotalPrice)
                .sum(); // Hitung total harga dari items
    }

    // Getters dan Setters
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class Item {
        private Long id;
        private Long productId;
        private String productName;
        private String productImage;
        private Integer quantity;
        private Double pricePerUnit;
        private Double totalPrice;

        public Item(Long id, Long productId, String productName, String productImage, Integer quantity, Double pricePerUnit, Double totalPrice) {
            this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.productImage = productImage;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
            this.totalPrice = totalPrice;
        }

        // Getters dan Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
