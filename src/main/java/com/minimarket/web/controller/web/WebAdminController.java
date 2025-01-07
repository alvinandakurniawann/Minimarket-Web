package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.CategoryRequest;
import com.minimarket.web.dto.request.ProductRequest;
import com.minimarket.web.dto.response.CategoryResponse;
import com.minimarket.web.dto.response.ProductResponse;
import com.minimarket.web.dto.response.TransactionResponse;
import com.minimarket.web.service.interfaces.CategoryService;
import com.minimarket.web.service.interfaces.ProductService;
import com.minimarket.web.service.interfaces.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class WebAdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;

    // Tampilkan dashboard admin
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    // Tampilkan daftar produk
    @GetMapping("/product/list")
    public String manageProducts(Model model) {
        List<ProductResponse> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/products/list";
    }

    // Tampilkan halaman tambah produk
    @GetMapping("/product/add")
    public String addProductPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productRequest", new ProductRequest());
        return "admin/products/add";
    }

    // Proses tambah produk
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest,
                             @RequestParam("image") MultipartFile image) {
        productService.addProductWithImage(productRequest, image);
        return "redirect:/admin/product/list";
    }

    // Tampilkan halaman edit produk
    @GetMapping("/product/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        ProductResponse product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/products/edit";
    }

    // Proses edit produk
    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              @ModelAttribute ProductRequest productRequest,
                              @RequestParam("image") MultipartFile image) {
        productService.updateProductWithImage(id, productRequest, image);
        return "redirect:/admin/product/list";
    }

    // Proses hapus produk
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product/list";
    }

    // Tampilkan daftar kategori
    @GetMapping("/category/list")
    public String manageCategories(Model model) {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/categories/list";
    }

    // Tampilkan halaman tambah kategori
    @GetMapping("/category/add")
    public String addCategoryPage(Model model) {
        model.addAttribute("categoryRequest", new CategoryRequest());
        return "admin/categories/add";
    }

    // Proses tambah kategori
    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
        return "redirect:/admin/category/list";
    }

    // Proses hapus kategori
    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category/list";
    }

    // Tampilkan halaman edit kategori
    @GetMapping("/category/edit/{id}")
    public String editCategoryPage(@PathVariable Long id, Model model) {
        System.out.println("Received ID: " + id);
        CategoryResponse category = categoryService.getCategoryById(id);
        model.addAttribute("categoryRequest", new CategoryRequest(category.getName()));
        model.addAttribute("categoryId", id); // Tambahkan ID ke model
        return "admin/categories/edit";
    }
    
    
    

    // Proses edit kategori
    @PostMapping("/category/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute CategoryRequest categoryRequest) {
        categoryService.updateCategory(id, categoryRequest);
        return "redirect:/admin/category/list";
    }
    

    // Tampilkan daftar transaksi
    @GetMapping("/transactions/list")
    public String manageTransactions(Model model) {
        List<TransactionResponse> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "admin/transactions/list";
    }

    // Tampilkan detail transaksi
    @GetMapping("/transactions/detail/{id}")
    public String transactionDetail(@PathVariable Long id, Model model) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "admin/transactions/d    etail";
    }
}
