package com.example.diploma_thesis.repository;


import com.example.diploma_thesis.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findBySoleTraderId(Long id);
}
