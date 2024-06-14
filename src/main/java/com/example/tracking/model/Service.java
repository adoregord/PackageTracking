package com.example.tracking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "service")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Service {
    //Service → IdService string, NamaService, HargaPerKg float'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;

    @Column(nullable = false)
    private String nama_service;

    @Column(nullable = false)
    private float hargaPerKg;
    
}
