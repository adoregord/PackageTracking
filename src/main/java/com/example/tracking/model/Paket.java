package com.example.tracking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "paket")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paket {
        //make IdPaket string, Pengirim pengirim, Penerima,TujuanPengiriman Lokasi, BeratPaket float
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_paket;

        @OneToOne
        @JoinColumn(name = "pengirim_id")
        private Pengirim pengirim;

        @OneToOne
        @JoinColumn(name = "penerima_id")
        private Penerima penerima;

        @OneToOne
        @JoinColumn(name = "tujuan_pengiriman_id", referencedColumnName = "id_lokasi")
        private Lokasi TujuanPengiriman;

        @Column(nullable = false)
        private float beratPaket;
}
