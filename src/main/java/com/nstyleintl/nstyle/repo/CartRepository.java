package com.nstyleintl.nstyle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nstyleintl.nstyle.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByCustomerCustomerId(Long customerId);
}

