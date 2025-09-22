package com.example.QueryDataExample.service;

import com.example.QueryDataExample.dto.request.CreateNewCustomerDTO;
import com.example.QueryDataExample.dto.request.GetCustomerByID;
import com.example.QueryDataExample.dto.request.UpdateCustomerDTO;
import com.example.QueryDataExample.dto.response.ListCustomerDTO;
import com.example.QueryDataExample.entity.Customer;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.UUID;


public interface CustomerService {
    void createNewCustomer(CreateNewCustomerDTO createNewCustomerDTO);
    Customer getCustomer(GetCustomerByID getCustomerByID) throws BadRequestException;
    Page<ListCustomerDTO> getListCustomer(Integer page, Integer size);
    void deleteCustomer(UUID customer_id);
    void updateCustomer(UUID customer_id, UpdateCustomerDTO updateCustomerDTO);
}
