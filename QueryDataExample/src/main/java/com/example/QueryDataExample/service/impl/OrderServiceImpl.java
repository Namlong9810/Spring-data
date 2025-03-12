package com.example.QueryDataExample.service.impl;

import com.example.QueryDataExample.dto.response.ListOrderAndCustomerName;
import com.example.QueryDataExample.entity.OrderProduct;
import com.example.QueryDataExample.repository.OrderRepository;
import com.example.QueryDataExample.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final Logger log;
//  private final CustomerRepository  customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, Logger log){
//        customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.log = log;
    }

    @Override
    public List<ListOrderAndCustomerName> getListOrder() {
        List<Object[]> listDetailOrder = orderRepository.getDetailOrder();
// Cách 1:     Ánh xạ dữ liệu  bằng cách sử dụng builder
//        return listDetailOrder.stream()
//                .map(data -> ListOrderAndCustomerName.builder()
//                                    .orderID((UUID) data[0])
//                                    .customerID((UUID) data[1])
//                                    .totalValue((Integer) data[2])
//                                    .customerName((String) data[3])
//                                    .build())
//                .collect(Collectors.toList());

//        ListOrderAndCustomerName a = ListOrderAndCustomerName.builder().build();
////Cách 2:      Ánh xạ dữ liệu list bằng module mapper
        return listDetailOrder.stream()
                .map(data -> convert(data, ListOrderAndCustomerName.class))
                .collect(Collectors.toList());
//        return null;
}

    @Override
    public void createNewOrder() {
        OrderProduct ord1 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 12);
        OrderProduct ord2 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 12);
        OrderProduct ord3 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 13);
        OrderProduct ord4 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 14);

        orderRepository.saveAll(List.of(ord1, ord2, ord3, ord4));
    }

    //Method thực hiện convert dữ liệu tránh lặp lại code
    private <S, T> T convert(S source, Class<T> targetClass){
        return modelMapper.map(source, targetClass);
    }
}
