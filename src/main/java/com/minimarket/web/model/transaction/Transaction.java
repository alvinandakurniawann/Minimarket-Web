package com.minimarket.web.model.transaction;

import com.minimarket.web.model.base.BaseEntity;
import com.minimarket.web.model.user.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.util.List;

@Entity
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionItem> items;

    @Column(nullable = false)
    @DecimalMin(value = "0.1", inclusive = true, message = "Total amount must be at least 0.1")
    private Double total;

    @Column(nullable = false, length = 20)
    private String status = "Pending"; // Default value    

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<TransactionItem> getItems() {
        return items;
    }

    public void setItems(List<TransactionItem> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
