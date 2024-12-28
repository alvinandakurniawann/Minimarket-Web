package com.minimarket.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        return "admin/dashboard"; // Mengarah ke src/main/resources/templates/admin/dashboard.html
    }

    @GetMapping("/admin/products")
    public String manageProducts(Model model) {
        return "admin/products/list"; // Mengarah ke src/main/resources/templates/admin/products/list.html
    }

    @GetMapping("/admin/transactions")
    public String manageTransactions(Model model) {
        return "admin/transactions/list"; // Mengarah ke src/main/resources/templates/admin/transactions/list.html
    }
}
