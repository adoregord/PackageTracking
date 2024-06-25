package com.example.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Penerima;
import com.example.tracking.service.PenerimaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Slf4j
@RequestMapping("/api/v1/penerima")
public class PenerimaController {

    @Autowired
    PenerimaService penerimaService;

    @GetMapping
    public ResponseEntity<List<Penerima>> getAllPenerima() {
       try {
            log.info("Menampilkan semua penerima");
            return ResponseEntity.ok(penerimaService.getAllPenerima());
        } 
        catch (Exception e) {
            e.getStackTrace();
            log.info("Gagal menampilkan semua penerima " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addPenerima(@RequestBody Penerima penerima) {
        try {
            log.info("Menambahkan penerima \n");
            return ResponseEntity.ok(penerimaService.addPenerima(penerima));
        } 
        catch (Exception e) {
            e.getStackTrace();
            log.info("Gagal menambahkan penerima " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        
    }
}
