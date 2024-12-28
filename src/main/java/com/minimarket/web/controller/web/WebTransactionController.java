package com.minimarket.web.controller.web;

import com.minimarket.web.dto.response.TransactionResponse;
import com.minimarket.web.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/customer/{customerId}")
    public String listTransactionsByCustomer(@PathVariable Long customerId, Model model) {
        List<TransactionResponse> transactions = transactionService.getAllTransactionsByCustomerId(customerId);
        model.addAttribute("transactions", transactions);
        return "transactions/list"; // Template HTML: src/main/resources/templates/transactions/list.html
    }

    @GetMapping("/{id}")
    public String viewTransactionDetails(@PathVariable Long id, Model model) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "transactions/detail"; // Template HTML: src/main/resources/templates/transactions/detail.html
    }
}
