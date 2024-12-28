package com.minimarket.web.repository;

import com.minimarket.web.model.transaction.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}
