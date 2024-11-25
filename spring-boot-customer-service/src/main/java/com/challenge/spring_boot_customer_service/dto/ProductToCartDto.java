package com.challenge.spring_boot_customer_service.dto;

import lombok.Data;

@Data
public class ProductToCartDto {
    private Long productId;
    private  Long cartId;
    private int amount;
}
