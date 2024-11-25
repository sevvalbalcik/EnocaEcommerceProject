package com.challenge.spring_boot_customer_service.repository;

import com.challenge.spring_boot_customer_service.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPurchaseHistoryRepository extends JpaRepository<PurchaseHistory,Long> {

    @Query("select p " +
            "from PurchaseHistory p " +
            "where p.orderCode = :code")
    List<PurchaseHistory> getAllProductWithCode(@Param("code") String code);
}
