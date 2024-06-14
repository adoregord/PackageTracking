package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    
}
