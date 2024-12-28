package com.minimarket.web.dto.response;

import java.util.List;

public class TransactionResponse {

    private Long id; // ID transaksi
    private String customerName; // Nama pelanggan
    private String paymentMethod; // Metode pembayaran (cash, debit, wallet)
    private Double total; // Total harga transaksi

    // Daftar item dalam transaksi
    private List<Item> items;

    // Inner class untuk item
    public static class Item {
        private Long productId; // ID produk
        private String productName; // Nama produk
        private Integer quantity; // Jumlah produk
        private Double pricePerUnit; // Harga per unit
        private Double totalPrice; // Harga total (quantity * pricePerUnit)

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

    // Constructor untuk TransactionResponse
    public TransactionResponse(Long id, String customerName, String paymentMethod, Double total, List<Item> items) {
        this.id = id;
        this.customerName = customerName;
        this.paymentMethod = paymentMethod;
        this.total = total;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
