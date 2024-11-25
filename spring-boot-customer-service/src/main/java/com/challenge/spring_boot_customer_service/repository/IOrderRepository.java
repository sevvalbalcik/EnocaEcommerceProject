package com.challenge.spring_boot_customer_service.repository;

import com.challenge.spring_boot_customer_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

    @Query("select o " +
            " from Order o inner join Customer c on c.id = o.customer.id " +
            " where o.customer.id = :customerId")
    List<Order> getOrderWithCustomerId(@Param("customerId") Long customerId);

    @Query("select o " +
            "from Order o join fetch o.customer c join fetch o.products p " +
            "where o.code = :code")
    List<Order> findByCode(@Param("code") String code);
}
