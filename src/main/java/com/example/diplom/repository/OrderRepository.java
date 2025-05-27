package com.example.diplom.repository;

import com.example.diplom.models.Client;
import com.example.diplom.models.Order;
import com.example.diplom.models.Supplier;
import com.example.diplom.models.enums.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findBySupplierId(Long id);
    List<Order> findBySupplier(Supplier supplier);
    List<Order> findByClient(Client client);
    List<Order> findBySupplierAndStatus(Supplier supplier, OrderStatus orderStatus);
    List<Order> findBySupplierAndStatusAndDateTimeAfter(Supplier supplier, OrderStatus orderStatus, LocalDateTime localDateTime);
}
