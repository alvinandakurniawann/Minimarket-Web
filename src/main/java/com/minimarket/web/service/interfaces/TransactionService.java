package com.minimarket.web.service.interfaces;

import com.minimarket.web.dto.request.TransactionRequest;
import com.minimarket.web.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);

    TransactionResponse getTransactionById(Long id);

    List<TransactionResponse> getAllTransactionsByCustomerId(Long customerId);

    List<TransactionResponse> getAllTransactions(); // Tambahkan metode untuk semua transaksi

    void updateTransactionStatus(Long id, String status);

}
