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
@Table(name = "pengirim")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pengirim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pengirim;

    @Column(nullable = false, unique = true)
    private String nama_pengirim;

    @Column(nullable = false)
    private String no_telp;
}
