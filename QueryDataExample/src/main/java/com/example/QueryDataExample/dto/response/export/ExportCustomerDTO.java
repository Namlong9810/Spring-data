package com.example.QueryDataExample.dto.response.export;


import com.example.QueryDataExample.annotation.Excel;
import lombok.Data;

import java.util.UUID;

@Data
public class ExportCustomerDTO {
    @Excel(name = "customer_id")
    private UUID customer_id;

    @Excel(name = "customer_name")
    private String customer_name;

//    @Excel(name = "email")
    private String email;
}
