package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.model.Product;

import java.util.Optional;

public interface IProductService {

    Product createProduct(Product product);
    Optional<Product> getProduct(Long id);
    Product updateProduct(Long id,Product product);
    void deleteProduct(Long id);

}
