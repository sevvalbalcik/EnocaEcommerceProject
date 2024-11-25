package com.challenge.spring_boot_customer_service.repository;

import com.challenge.spring_boot_customer_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByCustomerId(Long customerId);
}
