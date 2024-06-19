package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.DTO.CheckpointDTO;
import com.example.tracking.model.Pengiriman;
import com.example.tracking.service.PengirimanService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
@RequestMapping("/api/v1/pengiriman")
public class PengirimanController {

    @Autowired
    private PengirimanService pengirimanService;

    @GetMapping
    public ResponseEntity<List<Pengiriman>> getAllPengiriman() {
        try {
            log.info("Menampilkan semua pengiriman");
            return ResponseEntity.ok(pengirimanService.getAllPengiriman());
        } 
        catch (Exception e) {
            log.info("Gagal menampilkan semua pengiriman " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> addPengiriman(@RequestBody Pengiriman pengiriman) {
        try {
            log.info("Menambahkan pengiriman \n");
            return ResponseEntity.ok(pengirimanService.addPengiriman(pengiriman) + "Pengiriman dengan ID " + pengiriman.getId_pengiriman() + " telah ditambahkan");    
        } 
        catch (Exception e) {
            log.info("Gagal menambahkan pengiriman \n" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/arrived")
    public ResponseEntity<List<Pengiriman>> pengirimanSampai() {
        try {
            log.info("Menampilkan List pengiriman yang sudah sampai \n");
            return ResponseEntity.ok(pengirimanService.pengirimanSampai());
        } 
        catch (Exception e) {
            log.info("Gagal menampilkan list pengiriman " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    @PostMapping("/checkpoint")//pengiriman_id
    public ResponseEntity<?> addCheckpoint(@RequestBody CheckpointDTO checkpointDTO) {   
        try {
            log.info("Menambahkan checkpoint pada ID" + checkpointDTO.getLokasi_id());
             return ResponseEntity.ok(pengirimanService.addCheckpoint(checkpointDTO));
        } 
        catch (Exception e) {
            log.info("Gagal menambahkan checkpoint baru");
            return ResponseEntity.badRequest().build();
        }
    }
}
