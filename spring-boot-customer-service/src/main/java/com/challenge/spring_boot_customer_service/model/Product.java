package com.challenge.spring_boot_customer_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product extends Base{

    @Column(name = "code",nullable = false)
    private String code;

    @Column(name="name",nullable = false,length = 100)
    private String name;

    @Column(name="price",nullable = false)
    private Double price;

    @Column(name = "stock",nullable = false)
    private Integer stock;
}
