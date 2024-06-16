package com.example.tracking.model;


import org.hibernate.annotations.Formula;

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
public class Lokasi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_lokasi;

    private String namaLokasi;

    @Formula("CONCAT('<', nama_lokasi, '-', LPAD(CAST(id_lokasi AS CHAR), 4, '0'), '>')")
    private String alamat;
}
