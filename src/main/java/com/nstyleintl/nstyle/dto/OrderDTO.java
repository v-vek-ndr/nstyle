package com.nstyleintl.nstyle.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Long customerId;
    private List<Long> subCategoryIds;
    private BigDecimal totalBill;
    private String message;
}
