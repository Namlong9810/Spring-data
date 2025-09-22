package com.example.QueryDataExample.service.impl;

import com.example.QueryDataExample.dto.request.CreateNewCustomerDTO;
import com.example.QueryDataExample.dto.request.GetCustomerByID;
import com.example.QueryDataExample.dto.request.UpdateCustomerDTO;
import com.example.QueryDataExample.dto.response.ListCustomerDTO;
import com.example.QueryDataExample.entity.Customer;
import com.example.QueryDataExample.repository.CustomerRepository;
import com.example.QueryDataExample.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @CachePut(value= "customers", key = "#createNewCustomerDTO.customer_name" + '_' + "#createNewCustomerDTO.email")
    public void createNewCustomer(CreateNewCustomerDTO createNewCustomerDTO) {
        Customer newCustomer = Customer.builder()
                .customer_name(createNewCustomerDTO.getCustomer_name())
                .email(createNewCustomerDTO.getEmail())
                .build();
        customerRepository.save(newCustomer);
        log.info("Checking data {}", newCustomer);
    }

    @Override
    // Lấy dữ liệu và lưu vào cache
    @Cacheable(value = "customers", key = "#getCustomerByID.customer_id")
    public Customer getCustomer(GetCustomerByID getCustomerByID) throws BadRequestException {
        Customer getCustomer = customerRepository.findById(getCustomerByID.getCustomer_id())
                                .orElseThrow(() -> new BadRequestException("Customer Id not Exist"));
        log.info("get Customer information {}", getCustomer);
        return getCustomer;
    }

    @Override
    @Cacheable(value = "ListCustomer", key = "'list_customer'")
    public Page<ListCustomerDTO> getListCustomer(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> getListCustomer = customerRepository.findAll(pageable);

         List<ListCustomerDTO> listCustomerDTO = getListCustomer.stream()
                .map(data -> modelMapper.map(data, ListCustomerDTO.class))
                .collect(Collectors.toList());
         log.info("Checking data {}", listCustomerDTO);

        return new PageImpl<>(listCustomerDTO, pageable, getListCustomer.getTotalElements());
    }

    @Override
    @CacheEvict(value = "customers", key = "#customer_id")
    public void deleteCustomer(UUID customer_id) {
        log.info("Validating... customer id");
        try{
            customerRepository.findById(customer_id)
                    .orElseThrow(() -> new BadRequestException("Customer Id not Exist"));
        }catch (BadRequestException brq){
            System.out.println("Error: " + brq.getMessage());
        }

            customerRepository.deleteById(customer_id);
    }

    @Override
    // CachePut để cập nhật dữ liệu thay đổi vào cache rồi mới lưu vào db
    @CachePut(value = "customers", key = "#customer_id")
    public void updateCustomer(UUID customer_id, UpdateCustomerDTO updateCustomerDTO) {
        log.info("validating... customer id");
        try{
            customerRepository.findById(customer_id)
                    .orElseThrow(() -> new BadRequestException("Customer Id not Exist"));
        }catch (BadRequestException brq){
            System.out.println("Error: " + brq.getMessage());
        }

        try{
            customerRepository.updateCustomer(customer_id, updateCustomerDTO.getCustomer_id(), updateCustomerDTO.getEmail());
        }catch (Exception e){
            System.out.println("An Error: " + e.getMessage());
        }


    }

}
