package com.example.QueryDataExample.dto.response;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListOrderAndCustomerName {
    UUID orderID;

    UUID customerID;

    Integer totalValue;

    String customerName;
}
