package com.nstyleintl.nstyle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.OrderRequest;
import com.nstyleintl.nstyle.dto.OrderResponse;
import com.nstyleintl.nstyle.model.OrderHeader;
import com.nstyleintl.nstyle.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            OrderHeader savedOrder = orderService.createOrder(orderRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order created successfully");
            response.put("transactionId", savedOrder.getTransactionId());
            response.put("orderId", savedOrder.getOrderHeaderId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("transaction/{transactionId}")
    public OrderResponse getOrderByTransactionId(@PathVariable String transactionId) {
    	OrderHeader orderHeader = orderService.findOrderByTransactionId(transactionId);
        return OrderResponse.fromOrderHeader(orderHeader);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        OrderHeader orderHeader = orderService.getOrderById(orderId);
        OrderResponse response = OrderResponse.fromOrderHeader(orderHeader);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderRequest updatedOrderRequest) {
        OrderHeader updatedOrder = orderService.updateOrder(orderId, updatedOrderRequest);
        return ResponseEntity.ok(Map.of(
            "message", "Order updated successfully",
            "transactionId", updatedOrder.getTransactionId(),
            "orderId", updatedOrder.getOrderHeaderId()
        ));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order deleted successfully"));
    }
}


