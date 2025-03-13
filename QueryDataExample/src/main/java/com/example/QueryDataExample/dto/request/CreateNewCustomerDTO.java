package com.example.QueryDataExample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateNewCustomerDTO {
    String customer_name;
    String email;
}
