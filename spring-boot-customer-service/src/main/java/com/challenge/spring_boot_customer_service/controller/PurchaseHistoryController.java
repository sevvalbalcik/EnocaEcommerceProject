package com.challenge.spring_boot_customer_service.controller;

import com.challenge.spring_boot_customer_service.service.IPurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase_history")
public class PurchaseHistoryController {

    private IPurchaseHistoryService purchaseHistoryService;

    @Autowired
    public PurchaseHistoryController(IPurchaseHistoryService purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getAllProductWithCode(@PathVariable String code){
        return new ResponseEntity<>(purchaseHistoryService.getAllProductWithCode(code), HttpStatus.OK);
    }
}
