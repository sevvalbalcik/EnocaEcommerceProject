package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.dto.CartDto;
import com.challenge.spring_boot_customer_service.dto.ProductRemoveFromCartDto;
import com.challenge.spring_boot_customer_service.dto.ProductToCartDto;
import com.challenge.spring_boot_customer_service.model.Cart;
import com.challenge.spring_boot_customer_service.model.Product;

import java.util.Optional;

public interface ICartService {

    Cart getCart(Long customerId);

    public Cart updateCart(ProductToCartDto productToCartDto);

    Cart emptyCart(Long id);

    public Cart addProductToCart(ProductToCartDto productToCartDto);

    public Cart removeProductFromCart(ProductRemoveFromCartDto productRemoveFromCartDto);
}
