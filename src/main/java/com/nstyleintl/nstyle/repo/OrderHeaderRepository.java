package com.nstyleintl.nstyle.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nstyleintl.nstyle.model.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

	Optional<OrderHeader> findByTransactionId(String transactionId);
}

