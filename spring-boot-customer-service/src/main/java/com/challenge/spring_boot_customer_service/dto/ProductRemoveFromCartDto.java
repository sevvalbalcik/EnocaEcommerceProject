package com.challenge.spring_boot_customer_service.dto;

import lombok.Data;

@Data
public class ProductRemoveFromCartDto {
    private Long cartId;
    private Long productId;
    private int amount;
}
