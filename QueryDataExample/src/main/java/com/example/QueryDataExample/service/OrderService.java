package com.example.QueryDataExample.service;

import com.example.QueryDataExample.dto.response.ListOrderAndCustomerName;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    List<ListOrderAndCustomerName> getListOrder();
    void createNewOrder();
}
