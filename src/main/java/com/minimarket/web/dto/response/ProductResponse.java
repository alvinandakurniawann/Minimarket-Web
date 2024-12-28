package com.minimarket.web.dto.response;

public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private String categoryName;

    public ProductResponse(Long id, String name, Double price, Integer stock, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
