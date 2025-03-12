package com.example.QueryDataExample.repository;


import com.example.QueryDataExample.entity.OrderProduct;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, UUID> {

    @Query(value = "select UUID_FROM_BIN(op.order_id) as order_id, UUID_FROM_BIN(op" +
                    ".customer_id) as customer_id, op.total_value, " +
                    "ct.customer_name " +
                    "from customer ct \n" +
                    "inner join order_product op on ct.customer_id = op.customer_id", nativeQuery = true)
    List<Object[]> getDetailOrder();
}
