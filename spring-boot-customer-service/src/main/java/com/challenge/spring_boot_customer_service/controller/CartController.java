package com.challenge.spring_boot_customer_service.controller;

import com.challenge.spring_boot_customer_service.dto.CartDto;
import com.challenge.spring_boot_customer_service.dto.ProductRemoveFromCartDto;
import com.challenge.spring_boot_customer_service.dto.ProductToCartDto;
import com.challenge.spring_boot_customer_service.model.Cart;
import com.challenge.spring_boot_customer_service.model.Product;
import com.challenge.spring_boot_customer_service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id){
        Cart cart = cartService.getCart(id);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody ProductToCartDto productToCartDto){
        return new ResponseEntity<>(cartService.updateCart(productToCartDto),HttpStatus.CREATED);
    }

    @PutMapping("/empty/{id}")
    public ResponseEntity<?> emptyCart(@PathVariable Long id){
        return new ResponseEntity<>(cartService.emptyCart(id),HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public  ResponseEntity<?> addProductToCart(@RequestBody ProductToCartDto productToCartDto){
        return new ResponseEntity<>(cartService.addProductToCart(productToCartDto),HttpStatus.CREATED);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeProductFromCart(@RequestBody ProductRemoveFromCartDto productRemoveFromCartDto){
        return new ResponseEntity<>(cartService.removeProductFromCart(productRemoveFromCartDto),HttpStatus.CREATED);
    }
}
