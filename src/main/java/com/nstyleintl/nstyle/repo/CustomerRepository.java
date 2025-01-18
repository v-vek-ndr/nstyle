package com.nstyleintl.nstyle.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nstyleintl.nstyle.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
	Optional<Customer> findByEmail(String email);
}
