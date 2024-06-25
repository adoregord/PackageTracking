package com.example.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Paket;
import com.example.tracking.service.PaketService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Slf4j
@RequestMapping("/api/v1/paket")
public class PaketController {

    @Autowired
    private PaketService paketService;

    @GetMapping
    public ResponseEntity<List<Paket>> getAllPaket() {
        try {
            log.info("Menampilkan semua paket");
            return ResponseEntity.ok(paketService.getAllPaket());
        } catch (Exception e) {
            log.info("Gagal menampilkan paket" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paket> addPaket(@RequestBody Paket paket) {
        try {
            log.info("Menambahkan paket \n");
            return ResponseEntity.ok(paketService.addPaket(paket));
        } catch (Exception e) {
            log.info("Gagal menambahkan paket" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{checkpoint}")
    public ResponseEntity<List<Object>> getCheckpointPaket(@PathVariable("checkpoint") String checkpoint) {
        try {
            log.info("Menampilkan paket berdasarkan chekpoint " + checkpoint);
            return ResponseEntity.ok(paketService.getCheckpointPaket(checkpoint));
        } catch (Exception e) {
            log.info("Gagal menampilkan paket berdasarkan checkpoint " + checkpoint + " " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

}

    // @GetMapping("/checkpoint")
    // public List<Object> getCheckpointPaket() {
    // return paketRepository.getCheckpointPaket();
    // }
