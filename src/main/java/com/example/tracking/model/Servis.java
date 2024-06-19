package com.example.tracking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;

    @Column(nullable = false)
    private String namaService;

    @Column(nullable = false)
    private Float hargaPerKg;

}
