package com.nstyleintl.nstyle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nstyleintl.nstyle.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>, OrderDetailsCustomRepository {
	
}

