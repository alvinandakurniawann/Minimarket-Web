package com.minimarket.web.controller.web;

import com.minimarket.web.dto.request.CartRequest;
import com.minimarket.web.dto.request.TransactionRequest;
import com.minimarket.web.dto.response.CartResponse;
import com.minimarket.web.dto.response.TransactionResponse;
import com.minimarket.web.model.cart.CartItem;
import com.minimarket.web.model.user.Customer;
import com.minimarket.web.repository.CartItemRepository;
import com.minimarket.web.repository.UserRepository;
import com.minimarket.web.service.interfaces.CartItemService;
import com.minimarket.web.service.interfaces.CartService;
import com.minimarket.web.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@Controller
@RequestMapping("/customer/cart")
public class WebCartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Only customers can add products to the cart"));

        CartRequest cartRequest = new CartRequest();
        cartRequest.setProductId(productId);
        cartRequest.setCustomerId(customer.getId());
        cartRequest.setQuantity(quantity);
        cartService.addToCart(cartRequest);

        return "redirect:/customer/cart/view/" + customer.getId();
    }

    @GetMapping("/view/{customerId}")
    public String viewCart(@PathVariable Long customerId, Model model) {
        CartResponse cart = cartService.getCartByCustomerId(customerId);
        model.addAttribute("cart", cart);
        model.addAttribute("customerId", customerId);
        return "customer/cart/view";
    }

@PostMapping("/update/{cartItemId}")
@ResponseBody
public ResponseEntity<String> updateCartItem(
        @PathVariable Long cartItemId,
        @RequestBody Map<String, Integer> payload,
        Authentication authentication) {
    int quantity = payload.get("quantity");
    if (quantity <= 0) {
        return ResponseEntity.badRequest().body("Quantity must be greater than 0");
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    Customer customer = userRepository.findByEmail(userDetails.getUsername())
            .filter(user -> user instanceof Customer)
            .map(user -> (Customer) user)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    // Validasi bahwa cartItemId milik pelanggan
    CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

    if (!cartItem.getCart().getCustomer().getId().equals(customer.getId())) {
        return ResponseEntity.status(403).body("Unauthorized cart item update");
    }

    cartItemService.updateCartItem(cartItemId, quantity);
    return ResponseEntity.ok("Cart item updated successfully");
}


    @PostMapping("/remove/{cartItemId}")
    public String removeCartItem(@PathVariable Long cartItemId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        cartItemService.removeCartItem(cartItemId);
        return "redirect:/customer/cart/view/" + customer.getId();
    }

    @PostMapping("/checkout/{cartId}")
    public String proceedToCheckout(
            @PathVariable Long cartId,
            @RequestParam(value = "paymentMethod", required = false) String paymentMethod,
            Authentication authentication,
            Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CartResponse cart = cartService.getCartByCustomerId(customer.getId());
        if (!cart.getId().equals(cartId)) {
            throw new IllegalArgumentException("Invalid cart ID");
        }

        if (paymentMethod == null || paymentMethod.isEmpty()) {
            model.addAttribute("cart", cart);
            model.addAttribute("error", "Payment method is required.");
            return "customer/cart/checkout";
        }

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setCustomerId(customer.getId());
        transactionRequest.setPaymentMethod(paymentMethod);
        transactionRequest.setItems(cart.getItems().stream().map(item -> {
            TransactionRequest.Item reqItem = new TransactionRequest.Item();
            reqItem.setProductId(item.getProductId());
            reqItem.setQuantity(item.getQuantity());
            return reqItem;
        }).toList());

        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        cartService.clearCart(customer.getId());

        model.addAttribute("transaction", transactionResponse);

        // Redirect ke halaman selesai dengan instruksi pembayaran
        if ("CASH".equalsIgnoreCase(paymentMethod)) {
            model.addAttribute("paymentInstruction", "Silakan bayar barangnya dengan tunai.");
        } else if ("TRANSFER".equalsIgnoreCase(paymentMethod)) {
            model.addAttribute("paymentInstruction", "Silakan transfer ke rekening Bank Seabank Rusdi: 0852131498.");
        } else if ("QRIS".equalsIgnoreCase(paymentMethod)) {
            model.addAttribute("paymentInstruction", "Scan QRIS berikut untuk pembayaran.");
            model.addAttribute("qrisImage", "/images/qris.jpg"); // Pastikan gambar ada di direktori static/images/qris.jpg
        }

        // Tombol kembali ke beranda
        model.addAttribute("homeButton", true);

        return "customer/cart/payment-instruction";
    }

    @GetMapping("/checkout/{cartId}")
    public String checkoutPage(@PathVariable Long cartId, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = userRepository.findByEmail(userDetails.getUsername())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CartResponse cart = cartService.getCartByCustomerId(customer.getId());
        if (!cart.getId().equals(cartId)) {
            throw new IllegalArgumentException("Invalid cart ID");
        }

        model.addAttribute("cart", cart);
        return "customer/cart/checkout";
    }
}
