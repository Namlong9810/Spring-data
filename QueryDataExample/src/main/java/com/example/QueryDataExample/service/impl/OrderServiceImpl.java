package com.example.QueryDataExample.service.impl;

import com.example.QueryDataExample.dto.response.ListOrderAndCustomerName;
import com.example.QueryDataExample.entity.OrderProduct;
import com.example.QueryDataExample.repository.OrderRepository;
import com.example.QueryDataExample.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
//  private final CustomerRepository  customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper){
//      customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable(value = "list_customer", key = "'all_list_customer'")
    public List<ListOrderAndCustomerName> getListOrder() {
        List<Object[]> listDetailOrder = orderRepository.getDetailOrder();
        log.info("response Data {}", listDetailOrder);
// Cách 1:     Ánh xạ dữ liệu  bằng cách sử dụng builder
        return listDetailOrder.stream()
                .map(data -> ListOrderAndCustomerName.builder()
                                    .orderID((UUID) UUID.fromString(data[0].toString()))
                                    .customerID((UUID) UUID.fromString(data[1].toString()))
                                    .totalValue((Integer) data[2])
                                    .customerName((String) data[3])
                                    .build())
                .collect(Collectors.toList());

//        ListOrderAndCustomerName a = ListOrderAndCustomerName.builder().build();
//Cách 2:      Ánh xạ dữ liệu list bằng module mapper
//        return  listDetailOrder.stream()
//                .map(data -> this.convert(data, ListOrderAndCustomerName.class))
//                .collect(Collectors.toList());
//        return null;
}

    @Override
    public void createNewOrder() {
        OrderProduct ord1 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 12);
        OrderProduct ord5 = OrderProduct.builder()
                                        .customer_id(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"))
                                        .total_value(12)
                                        .build();
        OrderProduct ord2 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 12);
        OrderProduct ord3 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 13);
        OrderProduct ord4 = new OrderProduct(UUID.fromString("7788E3B1-F115-4F04-98E9-F1BA2FCC535C"), 14);

        orderRepository.saveAll(List.of(ord1, ord2, ord3, ord4, ord5));
    }


    //Method thực hiện convert dữ liệu tránh lặp lại code
    private <S, T> T convert(S source, Class<T> targetClass){
        return modelMapper.map(source, targetClass);
    }
}
