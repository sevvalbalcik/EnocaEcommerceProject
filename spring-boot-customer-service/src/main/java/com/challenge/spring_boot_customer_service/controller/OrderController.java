package com.challenge.spring_boot_customer_service.controller;

import com.challenge.spring_boot_customer_service.dto.OrderDto;
import com.challenge.spring_boot_customer_service.model.Order;
import com.challenge.spring_boot_customer_service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto)
    {
        return new ResponseEntity<>(orderService.placeOrder(orderDto), HttpStatus.CREATED);
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> getOrderForCode(@PathVariable String code){
        return new ResponseEntity<>(orderService.getOrderForCode(code),HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getAllOrdersForCustomer(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getAllOrdersForCustomer(id),HttpStatus.OK);
    }



}
