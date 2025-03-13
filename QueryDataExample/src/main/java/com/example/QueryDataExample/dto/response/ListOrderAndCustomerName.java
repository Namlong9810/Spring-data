package com.example.QueryDataExample.dto.response;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListOrderAndCustomerName implements Serializable {
    UUID orderID;

    UUID customerID;

    Integer totalValue;

    String customerName;
}
