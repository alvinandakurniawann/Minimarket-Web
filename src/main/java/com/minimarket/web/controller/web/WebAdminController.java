package com.minimarket.web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAdminController {

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        // Tambahkan atribut model jika diperlukan
        return "admin/dashboard"; // Mengarah ke src/main/resources/templates/admin/dashboard.html
    }

    @GetMapping("/admin/products")
    public String showProductList(Model model) {
        // Tambahkan atribut model jika diperlukan
        return "admin/product/list"; // Mengarah ke src/main/resources/templates/admin/product/list.html
    }

    @GetMapping("/admin/categories")
    public String showCategoryList(Model model) {
        return "admin/categories/list"; // Mengarah ke src/main/resources/templates/admin/categories/list.html
    }
}
