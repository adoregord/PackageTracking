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
@Table(name = "penerima")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Penerima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_penerima;

    @Column(nullable = false)
    private String nama_penerima;

    @Column(nullable = false)
    private String no_telp;

    // @OneToMany(mappedBy = "penerima")
    // private List<Paket> paket;
}
