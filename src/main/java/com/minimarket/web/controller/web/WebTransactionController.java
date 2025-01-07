package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.TransactionRequest;
import com.minimarket.web.dto.response.TransactionResponse;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.model.user.User;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.TransactionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/transactions")
public class WebTransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    // List transactions for customer
    @GetMapping("/customer")
    public String listTransactionsForCustomer(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!(user instanceof Customer)) {
            throw new RuntimeException("User is not a customer");
        }

        Customer customer = (Customer) user;
        List<TransactionResponse> transactions = transactionService.getAllTransactionsByCustomerId(customer.getId());
        model.addAttribute("transactions", transactions);
        return "customer/transactions/list";
    }

    // View transaction details for customer
    @GetMapping("/{id}")
    public String viewTransactionDetailsForCustomer(@PathVariable Long id, Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!(user instanceof Customer)) {
            throw new RuntimeException("User is not a customer");
        }

        TransactionResponse transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "customer/transactions/detail";
    }

    // List all transactions for admin
    @GetMapping("/admin")
    public String listAllTransactionsForAdmin(Model model) {
        List<TransactionResponse> allTransactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", allTransactions);
        return "admin/transactions/list";
    }

    // View transaction details for admin
    @GetMapping("/admin/{id}")
    public String adminTransactionDetail(@PathVariable Long id, Model model) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "admin/transactions/detail";
    }

    // Update transaction status
    @PostMapping("/admin/transactions/status/{id}")
    @ResponseBody
    public ResponseEntity<String> updateTransactionStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            String newStatus = payload.get("status");
            if (newStatus == null || newStatus.isEmpty()) {
                return ResponseEntity.badRequest().body("Status cannot be empty");
            }
    
            System.out.println("Transaction ID: " + id);
            System.out.println("New Status: " + newStatus);
    
            transactionService.updateTransactionStatus(id, newStatus);
            return ResponseEntity.ok("Status updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
    

        
    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok("Transaction created successfully");
    }


}
