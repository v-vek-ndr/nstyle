package com.nstyleintl.nstyle.service;

import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.CustomerDTO;
import com.nstyleintl.nstyle.model.Customer;
import com.nstyleintl.nstyle.repo.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public Long createCustomer(CustomerDTO customerDTO) {
        try {
            Customer customer = new Customer();
            customer.setName(customerDTO.getName());
            customer.setMobile(customerDTO.getMobile());
            customer.setEmail(customerDTO.getEmail());

            customer = customerRepository.save(customer);

            return customer.getCustomerId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create customer", e);
        }
    }
}
