package com.challenge.spring_boot_customer_service.repository;

import com.challenge.spring_boot_customer_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
}
