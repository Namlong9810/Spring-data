package com.example.QueryDataExample;

import com.example.QueryDataExample.dto.request.CreateNewCustomerDTO;
import com.example.QueryDataExample.dto.request.GetCustomerByID;
import com.example.QueryDataExample.dto.request.UpdateCustomerDTO;
import com.example.QueryDataExample.service.CustomerService;
import com.example.QueryDataExample.service.OrderService;
import com.example.QueryDataExample.service.impl.CustomerServiceImpl;
import com.example.QueryDataExample.service.impl.OrderServiceImpl;
import org.apache.coyote.BadRequestException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

@SpringBootApplication
@EnableCaching
public class QueryDataExampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(QueryDataExampleApplication.class, args);

		CustomerService customerService = context.getBean(CustomerServiceImpl.class);
		OrderService  orderService = context.getBean(OrderServiceImpl.class);

//		customerService.createNewCustomer(new CreateNewCustomerDTO("Hồ Như Long", "namlong9810@gmail.com"));

//		try {
//			customerService.getCustomer(new GetCustomerByID(UUID.fromString("2D950E3C-9C1D-4E03-BE10-CCA1688B8D69")));
//		}catch (BadRequestException brq){
//			System.out.println("Error" + brq.getMessage());
//
//		}

//		customerService.updateCustomer(UUID.fromString("2D950E3C-9C1D-4E03-BE10-CCA1688B8D69"), new UpdateCustomerDTO("Hồ Như Long", "namlong9810" +
//				"@gmail.com"));

//		orderService.createNewOrder();

		orderService.getListOrder();
		orderService.getListOrder();
	}

}
