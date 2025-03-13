package com.example.QueryDataExample.repository;

import com.example.QueryDataExample.entity.Customer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Modifying
    @Transactional
    @Query(value =
            "update customer ct " +
            "set ct.customer_name = :customer_name, " +
            "ct.email = :email " +
            "where ct.customer_id = :customer_id", nativeQuery = true)
    void updateCustomer(@Param("customer_id") UUID customer_id, @Param("customer_name") String customer_name, @Param("email") String email);
}
