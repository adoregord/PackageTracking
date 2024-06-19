package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Servis;

@Repository
public interface ServisRepository extends JpaRepository<Servis, Long> {
    
}
