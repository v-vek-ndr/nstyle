package com.nstyleintl.nstyle.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.CartDTO;
import com.nstyleintl.nstyle.exception.ResourceNotFoundException;
import com.nstyleintl.nstyle.model.Cart;
import com.nstyleintl.nstyle.model.Customer;
import com.nstyleintl.nstyle.model.Services;
import com.nstyleintl.nstyle.repo.CartRepository;
import com.nstyleintl.nstyle.repo.CustomerRepository;
import com.nstyleintl.nstyle.repo.ServicesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ServicesRepository servicesRepository;
    private final CustomerRepository customerRepository;

    public Cart saveCart(CartDTO cartDTO) {
        Services service = servicesRepository.findById(cartDTO.getServicesId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + cartDTO.getServicesId()));

        Customer customer = customerRepository.findById(cartDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + cartDTO.getCustomerId()));

        Cart cart = Cart.builder()
                .service(service)
                .customer(customer)
                .quantity(cartDTO.getQuantity())
                .addedDate(new Date())
                .build();

        return cartRepository.save(cart);
    }
    
    public List<CartDTO> getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(cart -> new CartDTO(
                		cart.getCartId(),
                        cart.getService().getServiceId(),
                        cart.getService().getServiceName(),
                        cart.getCustomer().getCustomerId(),
                        cart.getQuantity()
                ))
                .collect(Collectors.toList());
    }
    
    public void deleteCartById(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new RuntimeException("Cart item with ID " + cartId + " does not exist.");
        }
        cartRepository.deleteById(cartId);
    }
}

