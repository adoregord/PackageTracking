package com.example.tracking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Penerima;

@Repository
public interface PenerimaRepository extends JpaRepository<Penerima, Long> {
    
}
