package com.nstyleintl.nstyle.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.nstyleintl.nstyle.dto.OrderRequest;
import com.nstyleintl.nstyle.dto.OrderResponse;
import com.nstyleintl.nstyle.exception.ResourceNotFoundException;
import com.nstyleintl.nstyle.model.Customer;
import com.nstyleintl.nstyle.model.OrderDetails;
import com.nstyleintl.nstyle.model.OrderHeader;
import com.nstyleintl.nstyle.model.Services;
import com.nstyleintl.nstyle.repo.CustomerRepository;
import com.nstyleintl.nstyle.repo.OrderDetailsRepository;
import com.nstyleintl.nstyle.repo.OrderHeaderRepository;
import com.nstyleintl.nstyle.repo.ServicesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

	private final OrderHeaderRepository orderHeaderRepository;
	private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerRepository customerRepository;
    private final ServicesRepository servicesRepository;

    @Transactional
    public OrderHeader createOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        orderHeader.setTotalBill(orderRequest.getTotalBill());
        orderHeader.setMessage(orderRequest.getMessage());
        orderHeader.setTransactionId(UUID.randomUUID().toString());

        List<OrderDetails> orderDetailsList = orderRequest.getServicesId().stream()
                .map(serviceId -> {
                    Services service = servicesRepository.findById(serviceId)
                            .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrderHeader(orderHeader);
                    orderDetails.setServices(service);
                    return orderDetails;
                }).toList();

        orderHeader.setOrderDetails(orderDetailsList);

        return orderHeaderRepository.save(orderHeader);
    }
    
    public OrderHeader findOrderByTransactionId(String transactionId) throws ResourceNotFoundException {
        return orderHeaderRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with transaction ID: " + transactionId));
    }
    
    public OrderHeader getOrderById(Long orderId) {
        return orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
    }

    @Transactional
    public OrderHeader updateOrder(Long orderId, OrderRequest updatedOrderRequest) {
    	OrderHeader orderHeader = getOrderById(orderId);

        Customer customer = customerRepository.findById(updatedOrderRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + updatedOrderRequest.getCustomerId()));

        List<OrderDetails> newOrderDetails = updatedOrderRequest.getServicesId().stream()
                .map(serviceId -> {
                    Services services = servicesRepository.findById(serviceId)
                            .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + serviceId));
                    return OrderDetails.builder().services(services).orderHeader(orderHeader).build();
                }).collect(Collectors.toList());

        orderHeader.setCustomer(customer);
        orderHeader.setTotalBill(updatedOrderRequest.getTotalBill());
        orderHeader.setMessage(updatedOrderRequest.getMessage());

        orderHeader.getOrderDetails().clear();
        orderHeader.getOrderDetails().addAll(newOrderDetails);

        return orderHeaderRepository.save(orderHeader);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        OrderHeader orderHeader = getOrderById(orderId);
        orderDetailsRepository.deleteOrderDetailsByHeader(orderHeader);
        orderHeaderRepository.delete(orderHeader);
    }

	public List<OrderResponse> getAllOrders() {
		List<OrderHeader> allOrders = orderHeaderRepository.findAll();
		if (ObjectUtils.isEmpty(allOrders)) {
			throw new ResourceNotFoundException("Zero Orders");
		}
		return allOrders.stream()
				.map(orderHeader -> OrderResponse.fromOrderHeader(orderHeader))
				.collect(Collectors.toList());
	}
}
