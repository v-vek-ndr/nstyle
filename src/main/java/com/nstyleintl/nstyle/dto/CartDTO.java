package com.nstyleintl.nstyle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

	private Long cartId;
    private Long servicesId;
    private String servicesName;
    private Long customerId;
    private Integer quantity;
}

