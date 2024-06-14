package com.example.tracking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pengiriman")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pengiriman {
    // Pengiriman → IdPengiriman string, Service service,Paket paket, HargaPengiriman float, CheckpointPengiriman []Lokasi, IsReceived
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pengiriman;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "paket_id")
    private Paket paket;

    @Column(nullable = false)
    private float hargaPengiriman;

    @OneToMany
    private List<Lokasi> checkpointPengiriman;

    @Column(nullable = false)
    private boolean isReceived;
}
