package com.challenge.spring_boot_customer_service.dto;

import lombok.Data;

@Data
public class ProductAmountDto {
    private Long productId;
    private Integer amount;
}
