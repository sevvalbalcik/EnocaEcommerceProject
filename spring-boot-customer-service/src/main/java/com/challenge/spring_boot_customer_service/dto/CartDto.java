package com.challenge.spring_boot_customer_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private int amount;
    private Long productId;


}
