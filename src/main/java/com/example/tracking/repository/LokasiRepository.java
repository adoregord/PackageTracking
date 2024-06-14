package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Lokasi;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
    
}
