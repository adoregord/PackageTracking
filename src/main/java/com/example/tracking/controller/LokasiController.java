package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Lokasi;
import com.example.tracking.service.LokasiService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/lokasi")
@Slf4j
public class LokasiController {

    @Autowired
    LokasiService lokasiService;

    @GetMapping
    public ResponseEntity<List<Lokasi>> getAllLoc() {
        try {
            log.info("Menampilkan semua lokasi ");
            return ResponseEntity.ok(lokasiService.getAllLoc());
        } catch (Exception e) {
            log.info("Gagal menambahkan lokasi " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addLoc(@RequestBody Lokasi lokasi) {
        try {
            log.info("Menambahkan lokasi " + lokasi.getNamaLokasi());
            return ResponseEntity.ok(lokasiService.addLoc(lokasi) + "Berhasil menambahkan lokasi " + lokasi.getNamaLokasi());

        } catch (Exception e) {
            log.info("Gagal menambahkan lokasi " + e.getMessage());
            return ResponseEntity.badRequest().body(("Gagal menambahkan lokasi " + e.getMessage()));
        }
    }
    
    @GetMapping("/checkpoints")
    public ResponseEntity<List<Object>> getCheckpointLokasi() {
        try {
            log.info("Menampilkan checkpoint lokasi ");
            return ResponseEntity.ok(lokasiService.getCheckpointLokasi());
        } catch (Exception e) {
            log.info("Gagal menampilkan lokasi"+e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
}
