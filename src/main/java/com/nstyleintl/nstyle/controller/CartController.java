package com.nstyleintl.nstyle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.CartDTO;
import com.nstyleintl.nstyle.model.Cart;
import com.nstyleintl.nstyle.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO) {
        try {
            Cart savedCart = cartService.saveCart(cartDTO);
            return ResponseEntity.ok().body(Map.of(
                "message", "Cart saved successfully",
                "result", true,
                "data", savedCart
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", e.getMessage(),
                "result", false
            ));
        }
    }
    
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCartByCustomerId(@PathVariable Long customerId) {
        try {
        	List<CartDTO> cartItems = cartService.getCartByCustomerId(customerId);
            return ResponseEntity.ok().body(Map.of(
                "message", "Cart retrieved successfully",
                "result", true,
                "data", cartItems
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", e.getMessage(),
                "result", false
            ));
        }
    }
    
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCartById(@PathVariable Long cartId) {
        try {
            cartService.deleteCartById(cartId);
            return ResponseEntity.ok().body(Map.of(
                "message", "Cart item deleted successfully",
                "result", true
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", e.getMessage(),
                "result", false
            ));
        }
    }
    
    @DeleteMapping("/all/{customerId}")
    public ResponseEntity<?> deleteAllCartsByCustomerId(@PathVariable Long customerId) {
        try {
            cartService.deleteAllCartsByCustomerId(customerId);
            return ResponseEntity.ok().body(Map.of(
                "message", "All cart items deleted successfully",
                "result", true
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "message", e.getMessage(),
                "result", false
            ));
        }
    }
}

