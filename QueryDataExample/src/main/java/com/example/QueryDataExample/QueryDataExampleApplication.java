package com.example.QueryDataExample;

import com.example.QueryDataExample.service.CustomerService;
import com.example.QueryDataExample.service.OrderService;
import com.example.QueryDataExample.service.impl.CustomerServiceImpl;
import com.example.QueryDataExample.service.impl.OrderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class QueryDataExampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(QueryDataExampleApplication.class, args);

		CustomerService customerService = context.getBean(CustomerServiceImpl.class);
		OrderService  orderService = context.getBean(OrderServiceImpl.class);

//		customerService.createNewCustomer();
//		orderService.createNewOrder();

		orderService.getListOrder();
	}

}
