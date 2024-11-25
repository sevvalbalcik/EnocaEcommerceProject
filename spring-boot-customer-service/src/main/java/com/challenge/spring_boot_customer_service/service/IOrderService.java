package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.dto.OrderDto;
import com.challenge.spring_boot_customer_service.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(OrderDto orderDto);
    List<Order> getOrderForCode(String code);
    List<Order> getAllOrdersForCustomer(Long customerId);
}
