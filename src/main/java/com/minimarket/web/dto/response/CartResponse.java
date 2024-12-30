package com.minimarket.web.dto.response;

import java.util.List;

public class CartResponse {

    private Long id; // ID keranjang
    private String customerName; // Nama pelanggan
    private List<Item> items; // Daftar item dalam keranjang
    private String totalPrice; // Total harga dalam format Rupiah

    public CartResponse(Long id, String customerName, List<Item> items, String totalPrice) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.totalPrice = totalPrice;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class Item {
        private Long id; // ID CartItem
        private Long productId;
        private String productName;
        private String productImage;
        private Integer quantity;
        private String pricePerUnit; // Harga satuan dalam Rupiah
        private String totalPrice; // Harga total dalam Rupiah

        public Item(Long id, Long productId, String productName, String productImage, Integer quantity, String pricePerUnit, String totalPrice) {
            this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.productImage = productImage;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
            this.totalPrice = totalPrice;
        }

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

        public String getPricePerUnit() {
            return pricePerUnit;
        }

        public void setPricePerUnit(String pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
