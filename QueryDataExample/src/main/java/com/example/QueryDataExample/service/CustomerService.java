package com.example.QueryDataExample.service;

import com.example.QueryDataExample.dto.request.CreateNewCustomerDTO;
import com.example.QueryDataExample.dto.request.GetCustomerByID;
import com.example.QueryDataExample.dto.request.UpdateCustomerDTO;
import com.example.QueryDataExample.entity.Customer;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface CustomerService {
    void createNewCustomer(CreateNewCustomerDTO createNewCustomerDTO);
    Customer getCustomer(GetCustomerByID getCustomerByID) throws BadRequestException;
    void deleteCustomer(UUID customer_id);
    void updateCustomer(UUID customer_id, UpdateCustomerDTO updateCustomerDTO);
}
