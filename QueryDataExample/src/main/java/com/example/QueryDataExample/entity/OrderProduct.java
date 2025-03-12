package com.example.QueryDataExample.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "OrderProduct")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProduct {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID order_id;

    @Column(name = "customer_id", nullable = false)
    private UUID customer_id;

    @Column(name = "total_value")
    private Integer total_value;

    public OrderProduct(UUID customer_id, Integer total_value){
        this.customer_id = customer_id;
        this.total_value = total_value;
    }
}
