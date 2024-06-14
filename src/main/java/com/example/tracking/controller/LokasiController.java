package com.example.tracking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Lokasi;
import com.example.tracking.repository.LokasiRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/lokasi")
public class LokasiController {

    @Autowired
    LokasiRepository lokasiRepository;

    @GetMapping
    public List<Lokasi> getAllLoc() {
        return lokasiRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addLoc(@RequestBody Lokasi lokasi) {
        lokasiRepository.save(lokasi);
        return ResponseEntity.ok("Success adding\n" + lokasi);
    }
    
    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
}
