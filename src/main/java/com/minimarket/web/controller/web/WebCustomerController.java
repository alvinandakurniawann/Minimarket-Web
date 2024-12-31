package com.minimarket.web.controller.web;

import com.minimarket.web.dto.response.CartResponse;
import com.minimarket.web.dto.response.CategoryResponse;
import com.minimarket.web.dto.response.ProductResponse;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.CartService;
import com.minimarket.web.service.interfaces.CategoryService;
import com.minimarket.web.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebCustomerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;
    
    

    @GetMapping("/customer/home")
    public String homePage(Model model) {
        List<ProductResponse> products = productService.getAllProducts(null, null);
        model.addAttribute("products", products);
        return "customer/home";
    }

    @GetMapping("/customer/cart/view")
    public String viewCart(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CartResponse cart = cartService.getCartByCustomerId(customer.getId());
        model.addAttribute("cart", cart);
        model.addAttribute("customerId", customer.getId());
        return "customer/cart/view"; // Ensure this matches the HTML file location
    }



    @GetMapping("/customer/products/list")
    public String showProductList(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "sort", required = false) String sort,
            Model model) {
        List<ProductResponse> products = productService.getAllProducts(categoryId, sort);
        List<CategoryResponse> categories = categoryService.getAllCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", categoryId);
        model.addAttribute("sortOrder", sort);

        return "customer/products/list";
    }

    @GetMapping("/customer/products/detail/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        ProductResponse product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "customer/products/detail";
    }
    
}
