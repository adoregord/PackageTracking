package com.example.tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracking.model.Paket;
import com.example.tracking.repository.PaketRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/paket")
public class PaketController {
    @Autowired
    private PaketRepository paketRepository;
    
    @GetMapping
    public List<Paket> getAllPaket() {
        return paketRepository.findAll();
    }
    
    @PostMapping
    public Paket postMethodName(@RequestBody Paket paket) {
        return paketRepository.save(paket);
    }
    
}
