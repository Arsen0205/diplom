package com.example.diploma_thesis.repository;


import com.example.diploma_thesis.models.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRequestRepository  extends JpaRepository<SupportRequest, Long> {
    List<SupportRequest> findByUserLogin(String userLogin);
}
