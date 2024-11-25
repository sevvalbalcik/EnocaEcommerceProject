package com.challenge.spring_boot_customer_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time",nullable = false,updatable = false)
    private LocalDateTime createdTime = LocalDateTime.now();

    @Column(name = "updated_time",nullable = false)
    private LocalDateTime updatedTime = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        updatedTime = LocalDateTime.now();
    }
}
