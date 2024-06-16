package com.example.tracking.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pengiriman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pengiriman;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "paket_id", nullable = false)
    private Paket paket;

    private float hargaPengiriman;

    @ElementCollection
    @CollectionTable(name = "checkpoint_pengiriman", joinColumns = @JoinColumn(name = "pengiriman_id"))
    private List<Lokasi> checkpointPengiriman;

    private boolean isReceived;
}
