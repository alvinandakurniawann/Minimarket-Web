package com.minimarket.web.repository;

import com.minimarket.web.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email); // Perbarui menjadi findByEmail
}
