package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.model.Customer;
import com.challenge.spring_boot_customer_service.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private ICustomerRepository  customerRepository;

    @Override
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
