package com.challenge.spring_boot_customer_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "orders")
public class Order extends Base{

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

    )
    private List<Product> products = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "order_product_amount", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "amount")
    private Map<Long,Integer> productAmounts = new HashMap<>();

    @Column(name = "total_price",nullable = false)
    private Double totalPrice;

    @Column(name = "code")
    private String code;

    @JsonIgnore
    public void addAmount(Long productId, int amount) {
        productAmounts.put(productId, amount);
    }


}
