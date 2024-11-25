package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.model.Order;
import com.challenge.spring_boot_customer_service.model.PurchaseHistory;
import com.challenge.spring_boot_customer_service.repository.IPurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService{

    @Autowired
    private IPurchaseHistoryRepository purchaseHistoryRepository;

    @Override
    public List<PurchaseHistory> getAllProductWithCode(String orderCode){
        return purchaseHistoryRepository.getAllProductWithCode(orderCode);
    }

}
