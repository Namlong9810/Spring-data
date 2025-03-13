package com.example.QueryDataExample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCustomerDTO {
    String customer_id;
    String email;
}
