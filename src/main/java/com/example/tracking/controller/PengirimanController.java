package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Pengiriman;
import com.example.tracking.repository.PengirimanRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/api/v1/pengiriman")
public class PengirimanController {
    @Autowired
    private PengirimanRepository pengirimanRepository;

    @GetMapping
    public List<Pengiriman> getAllPengiriman() {
        return pengirimanRepository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> addPengiriman(@RequestBody Pengiriman pengiriman) {
        pengirimanRepository.save(pengiriman);
        return ResponseEntity.ok("Success adding\n" + pengiriman); 
    }

    @GetMapping("/arrived")
    public List<Pengiriman> pengirimanSampai() {
        return pengirimanRepository.findByStatusPengiriman();
    }
    
    
}
