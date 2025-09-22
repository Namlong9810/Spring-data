package com.example.QueryDataExample.controller;


import com.example.QueryDataExample.dto.response.ListCustomerDTO;
import com.example.QueryDataExample.service.CustomerService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("list")
    public Page<ListCustomerDTO> getListCustomer(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "5") Integer size){
        return customerService.getListCustomer(page, size);
    }
    @DeleteMapping(":id")
    public void deleteCustomer(@RequestParam("id") UUID customer_id){
        customerService.deleteCustomer(customer_id);
    }
}
