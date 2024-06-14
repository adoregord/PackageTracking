package com.example.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking.model.Paket;

@Repository
public interface PaketRepository extends JpaRepository<Paket, Long>{

}  

