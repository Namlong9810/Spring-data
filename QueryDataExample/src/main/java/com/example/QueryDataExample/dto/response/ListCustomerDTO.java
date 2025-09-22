package com.example.QueryDataExample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListCustomerDTO {
    private UUID customer_id;

    private String customer_name;

    private String email;
}
