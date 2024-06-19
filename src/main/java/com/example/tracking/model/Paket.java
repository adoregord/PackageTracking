package com.example.tracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_paket;

    private String beratPaket;

    @ManyToOne
    @JoinColumn(name = "id_pengirim")
    private Pengirim pengirim;

    @ManyToOne
    @JoinColumn(name = "id_penerima")
    private Penerima penerima;

    @ManyToOne
    @JoinColumn(name = "id_tujuan_pengiriman")
    private Lokasi tujuanPengiriman;
}
