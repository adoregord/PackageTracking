package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Servis;
import com.example.tracking.service.ServisService;

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
@RequestMapping("/api/v1/service")
public class ServisController {
    
    @Autowired
    ServisService servisService;

    @GetMapping
    public ResponseEntity<List<Servis>> getAllService() {
        try {
            log.info("Menampilkan semua service");
            return ResponseEntity.ok(servisService.getAllService());      
        } 
        catch (Exception e) {
            e.getStackTrace();
            log.info("Gagal menampilkan service" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addServis(@RequestBody Servis servis) {
        try {
            log.info("Menambahkan service/jasa \n");
            return ResponseEntity.ok(servisService.addServis(servis));
        } 
        catch (Exception e) {
            e.getStackTrace();
            log.info("Gagal menambahkan servis " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
}
