package com.example.QueryDataExample.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id

    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customer_id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "email")
    private String email;

    public Customer(String customer_name, String email){
        this.customer_name = customer_name;
        this.email = email;
    }
}
