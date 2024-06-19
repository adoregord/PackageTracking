package com.example.tracking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Penerima {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_penerima;

    private String namaPenerima;
    private String noTelp;

}
