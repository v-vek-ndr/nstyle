package com.nstyleintl.nstyle.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.nstyleintl.nstyle.model.OrderHeader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

	private Long orderId;
    private String transactionId;
    private Long customerId;
    private BigDecimal totalBill;
    private String message;
    private List<Long> servicesIds;
    
    public static OrderResponse fromOrderHeader(OrderHeader orderHeader) {
        List<Long> servicesIds = orderHeader.getOrderDetails().stream()
                .map(orderDetail -> orderDetail.getServices().getServiceId())
                .collect(Collectors.toList());

        return new OrderResponse(
                orderHeader.getOrderHeaderId(),
                orderHeader.getTransactionId(),
                orderHeader.getCustomer().getCustomerId(),
                orderHeader.getTotalBill(),
                orderHeader.getMessage(),
                servicesIds
        );
    }
}
