package com.minimarket.web.controller.api;

import com.minimarket.web.dto.request.TransactionRequest;
import com.minimarket.web.dto.response.TransactionResponse;
import com.minimarket.web.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse response = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long id) {
        TransactionResponse response = transactionService.getTransactionById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByCustomerId(@PathVariable Long customerId) {
        List<TransactionResponse> response = transactionService.getAllTransactionsByCustomerId(customerId);
        return ResponseEntity.ok(response);
    }
}
