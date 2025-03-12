package com.example.QueryDataExample.service.impl;

import com.example.QueryDataExample.entity.Customer;
import com.example.QueryDataExample.repository.CustomerRepository;
import com.example.QueryDataExample.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createNewCustomer() {
        Customer new1 = new Customer("Ho Manh Nam", "namlong9810@gmail.com" );
        customerRepository.saveAll(List.of(new1));
    }
}
