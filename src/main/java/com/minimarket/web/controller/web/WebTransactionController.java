package com.minimarket.web.controller.web;

import com.minimarket.web.dto.response.TransactionResponse;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.model.user.User;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class WebTransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    // List all transactions for the authenticated customer
    @GetMapping("/customer")
    public String listTransactionsForCustomer(Authentication authentication, Model model) {
        String email = authentication.getName(); // Ambil email dari autentikasi
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!(user instanceof Customer)) {
            throw new RuntimeException("User is not a customer");
        }

        Customer customer = (Customer) user;
        Long customerId = customer.getId();
        List<TransactionResponse> transactions = transactionService.getAllTransactionsByCustomerId(customerId);
        model.addAttribute("transactions", transactions);
        return "customer/transactions/list"; // Template HTML untuk riwayat transaksi pelanggan
    }

    // View details of a specific transaction
    @GetMapping("/{id}")
    public String viewTransactionDetails(@PathVariable Long id, Model model) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "customer/transactions/detail"; // Perbarui path ini
    }
    

    // List all transactions for admin
    @GetMapping("/admin")
    public String listAllTransactionsForAdmin(Model model) {
        List<TransactionResponse> allTransactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", allTransactions);
        return "admin/transactions/list"; // Template HTML untuk riwayat transaksi admin
    }
    // Admin Transaction Detail
    @GetMapping("/admin/{id}")
    public String adminTransactionDetail(@PathVariable Long id, Model model) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "admin/transactions/detail"; // Path ke template
    }
    
}
