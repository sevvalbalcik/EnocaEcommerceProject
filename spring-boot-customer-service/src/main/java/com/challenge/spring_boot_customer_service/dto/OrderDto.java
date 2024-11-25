package com.challenge.spring_boot_customer_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long customerId;
    private List<ProductAmountDto> products;
    private Double totalPrice;
    private String code;
}
