package com.example.diploma_thesis.repository;


import com.example.diploma_thesis.models.SoleTrader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoleTraderRepository extends JpaRepository<SoleTrader, Long> {
    Optional<SoleTrader> findByLogin(String login);
}
