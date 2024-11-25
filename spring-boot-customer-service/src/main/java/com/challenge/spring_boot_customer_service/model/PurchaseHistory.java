package com.challenge.spring_boot_customer_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_history")
public class PurchaseHistory extends Base{

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name = "order_code")
    private String orderCode;
}
