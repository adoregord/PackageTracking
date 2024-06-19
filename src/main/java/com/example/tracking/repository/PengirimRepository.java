package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Pengirim;

@Repository
public interface PengirimRepository extends JpaRepository<Pengirim, Long> {
    
}
