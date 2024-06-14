package com.example.tracking.model;

import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "lokasi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})//, "pengiriman"})
public class Lokasi {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id_lokasi;

    @Column(nullable = false)
    private String nama_lokasi;

    @Formula("CONCAT('<',nama_lokasi, '-', id_lokasi,'>')")
    private String formatIdLokasi;

    // @ManyToOne
    // @JoinColumn(name = "pengiriman_id")
    // private Pengiriman pengiriman;
}
