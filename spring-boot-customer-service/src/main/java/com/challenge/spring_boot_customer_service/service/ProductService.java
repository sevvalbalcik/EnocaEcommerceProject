package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.model.Product;
import com.challenge.spring_boot_customer_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product createProduct(Product product){
        product.setCreatedTime((LocalDateTime.now()));
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Long id,Product productRequest){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product not found"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
