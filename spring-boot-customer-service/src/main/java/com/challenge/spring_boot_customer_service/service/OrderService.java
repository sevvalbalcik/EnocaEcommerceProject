package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.dto.OrderDto;
import com.challenge.spring_boot_customer_service.dto.ProductAmountDto;
import com.challenge.spring_boot_customer_service.model.Customer;
import com.challenge.spring_boot_customer_service.model.Order;
import com.challenge.spring_boot_customer_service.model.Product;
import com.challenge.spring_boot_customer_service.model.PurchaseHistory;
import com.challenge.spring_boot_customer_service.repository.ICustomerRepository;
import com.challenge.spring_boot_customer_service.repository.IOrderRepository;
import com.challenge.spring_boot_customer_service.repository.IProductRepository;
import com.challenge.spring_boot_customer_service.repository.IPurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IPurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public Order placeOrder(OrderDto orderDto){
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("customer not found"));
        Order order = new Order();
        order.setCustomer(customer);
        order.setCode(orderDto.getCode());

        for(ProductAmountDto productAmount : orderDto.getProducts()){
            Product product = productRepository.findById(productAmount.getProductId())
                    .orElseThrow(()-> new RuntimeException("product not found"));
            if(product.getStock() < productAmount.getAmount()){
                throw  new RuntimeException("Insufficient stock for product: " + product.getStock());
            }

            product.setStock(product.getStock() - productAmount.getAmount());
            productRepository.save(product);


            order.getProducts().add(product);
            order.addAmount(product.getId(), productAmount.getAmount());
            order.setTotalPrice(productAmount.getAmount()*product.getPrice());
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setOrderCode(order.getCode());
            purchaseHistory.setPrice(product.getPrice());
            purchaseHistory.setAmount(productAmount.getAmount());
            purchaseHistory.setProduct(product);
            purchaseHistoryRepository.save(purchaseHistory);
        }


        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderForCode(String code){
        return orderRepository.findByCode(code);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId){
        return orderRepository.getOrderWithCustomerId(customerId);
    }
}
