package com.nstyleintl.nstyle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.CustomerDTO;
import com.nstyleintl.nstyle.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Long> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Long customerId = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(customerId);
    }
}

