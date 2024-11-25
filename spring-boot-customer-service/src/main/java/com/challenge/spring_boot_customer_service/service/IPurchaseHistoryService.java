package com.challenge.spring_boot_customer_service.service;

import com.challenge.spring_boot_customer_service.model.Order;
import com.challenge.spring_boot_customer_service.model.PurchaseHistory;

import java.util.List;

public interface IPurchaseHistoryService {
    List<PurchaseHistory> getAllProductWithCode(String orderCode);
}
