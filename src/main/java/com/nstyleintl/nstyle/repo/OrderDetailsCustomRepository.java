package com.nstyleintl.nstyle.repo;

import com.nstyleintl.nstyle.model.OrderHeader;

public interface OrderDetailsCustomRepository {

	void deleteOrderDetailsByHeader(OrderHeader orderHeader);
}
