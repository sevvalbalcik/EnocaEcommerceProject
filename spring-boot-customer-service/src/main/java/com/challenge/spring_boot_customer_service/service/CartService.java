package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.dto.CartDto;
import com.challenge.spring_boot_customer_service.dto.ProductRemoveFromCartDto;
import com.challenge.spring_boot_customer_service.dto.ProductToCartDto;
import com.challenge.spring_boot_customer_service.model.Cart;
import com.challenge.spring_boot_customer_service.model.Customer;
import com.challenge.spring_boot_customer_service.model.Product;
import com.challenge.spring_boot_customer_service.repository.ICartRepository;
import com.challenge.spring_boot_customer_service.repository.ICustomerRepository;
import com.challenge.spring_boot_customer_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Cart getCart(Long customerId) {
        return cartRepository.findByCustomerId(customerId).orElseGet(() -> {
            Cart cart = new Cart();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(()->new RuntimeException("not found"));
            cart.setTotalPrice(0.0);
            cart.setCustomer(customer);
            return cartRepository.save(cart);
        });
    }



    @Override
    public Cart updateCart(ProductToCartDto productToCartDto) {
        Cart cart = cartRepository.findById(productToCartDto.getCartId())
                .orElseThrow(()-> new RuntimeException("not found"));
        Product product = productRepository.findById(productToCartDto.getProductId())
                .orElseThrow(()-> new RuntimeException("not found"));

        if(product.getStock() < productToCartDto.getAmount()){
            throw  new RuntimeException("Insufficient stock for product : " + product.getStock());
        }

        if(!cart.getProducts().contains(product)){
            throw  new RuntimeException("product not found");
        }

        if (productToCartDto.getAmount() < 1) {
            throw new RuntimeException("Product amount must be at least 1.");
        }

        cart.getProductAmounts().put(productToCartDto.getProductId(), productToCartDto.getAmount());

        product.setStock(product.getStock() - productToCartDto.getAmount());

        double totalPrice = cart.getProducts().stream()
                .mapToDouble(p -> p.getPrice() * cart.getProductAmounts().get(p.getId()))
                .sum();
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    @Override
    public Cart emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("not found"));

        cart.getProductAmounts().forEach((productId, amount) -> {
            Product product = productRepository.findById(productId)
                    .orElseThrow(()-> new RuntimeException("Product not found"));
            product.setStock(product.getStock()+amount);
        });
        cart.getProducts().clear();
        cart.getProductAmounts().clear();
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(ProductToCartDto productToCartDto){
        Cart cart = cartRepository.findById(productToCartDto.getCartId())
                .orElseThrow(()-> new RuntimeException("not found"));
        Product product = productRepository.findById(productToCartDto.getProductId())
                        .orElseThrow(()-> new RuntimeException("not found"));

        if(product.getStock() < productToCartDto.getAmount()){
            throw  new RuntimeException("Insufficient stock for product : " + product.getStock());
        }

        if(!cart.getProducts().contains(product)){
            cart.getProducts().add(product);
        }
        cart.getProductAmounts().merge(productToCartDto.getProductId(), productToCartDto.getAmount(), Integer::sum);

        product.setStock(product.getStock()-productToCartDto.getAmount());
        double totalPrice = cart.getProducts().stream()
                        .mapToDouble(p->p.getPrice()*cart.getProductAmounts().get(p.getId()))
                                .sum();
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(ProductRemoveFromCartDto productRemoveFromCartDto){
        Cart cart = cartRepository.findById(productRemoveFromCartDto.getCartId())
                .orElseThrow(()-> new RuntimeException("not found"));
        Product product = productRepository.findById(productRemoveFromCartDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("not found"));

        if(!cart.getProductAmounts().containsKey(productRemoveFromCartDto.getProductId())){
            throw  new RuntimeException("Product not in cart");
        }

        int currentAmount = cart.getProductAmounts()
                .get(productRemoveFromCartDto.getProductId());

        if(productRemoveFromCartDto.getAmount() >= currentAmount){
            cart.getProducts().remove(product);
            cart.getProductAmounts().remove(productRemoveFromCartDto.getProductId());
        } else{
            cart.getProductAmounts()
                    .put(productRemoveFromCartDto.getProductId(), currentAmount-productRemoveFromCartDto.getAmount());
        }

        product.setStock(product.getStock() + productRemoveFromCartDto.getAmount());

        double totalPrice = cart.getProducts().stream()
                        .mapToDouble(p->p.getPrice() * cart.getProductAmounts().get(p.getId()))
                                .sum();

        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }

}
